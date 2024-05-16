/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.crosscut.excel;

import com.mall.crosscut.excel.convert.Converter;
import com.mall.crosscut.excel.annotation.Column;
import com.mall.crosscut.excel.annotation.Filter;
import com.mall.crosscut.excel.annotation.Freeze;
import com.mall.crosscut.excel.annotation.Statistics;
import com.mall.crosscut.excel.util.ObjectUtil;
import com.mall.crosscut.excel.util.ReflectionUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

public class Excel {

    private static FormulaEvaluator formulaEvaluator;

    public static <T> List<T> read(String excelFile, Class<T> clazz) throws IOException {
        return read(excelFile, clazz, null);
    }

    public static <T> List<T> read(String excelFile, Class<T> clazz, Converter converter) throws IOException {
        return read(excelFile, 1, 0, clazz, converter);
    }

    public static <T> List<T> read(String excelFile, int startRow, int sheetIndex, Class<T> clazz, Converter converter)
            throws IOException {
        return read(createWorkbook(excelFile), startRow, sheetIndex, clazz, converter);
    }

    public static <T> List<T> read(InputStream inputStream, Class<T> clazz) throws IOException {
        return read(inputStream, clazz, null);
    }

    public static <T> List<T> read(InputStream inputStream, Class<T> clazz, Converter converter) throws IOException {
        return read(inputStream, 1, 0, clazz, converter);
    }

    public static <T> List<T> read(InputStream inputStream, int startRow, int sheetIndex, Class<T> clazz,
            Converter converter)
            throws IOException {
        return read(createWorkbook(inputStream), startRow, sheetIndex, clazz, converter);
    }

    public static <T> List<T> read(Workbook workbook, int startRow, int sheetIndex, Class<T> clazz,
            Converter converter) {
        // currently, only handle sheet one (or call side using foreach to support multiple sheet)
        Sheet sheet = workbook.getSheetAt(sheetIndex);

        Field[] fields = ReflectionUtil.getFields(clazz);
        Column[] columns = new Column[fields.length];
        // cache the @Column annotation
        for (int i = 0; i < fields.length; i++) {
            if (columns[i] == null) {
                Field field = fields[i];
                if (field.isAnnotationPresent(Column.class)) {
                    columns[i] = field.getAnnotation(Column.class);
                }
            }
        }

        // get the constructor
        Constructor<T> ctor;
        try {
            ctor = ReflectionUtil.accessibleConstructor(clazz);
        } catch (Throwable cause) {
            throw new RuntimeException(clazz.getSimpleName() + " must provide public parameterless constructor", cause);
        }

        List<T> items = new ArrayList<>();

        Row headerRow = null;
        int rowIndex = 0;
        Iterator<Row> iterator = sheet.rowIterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            if (rowIndex == 0) {
                headerRow = row;
            }
            rowIndex++;

            if (row.getRowNum() < startRow) {
                continue;
            }

            // TODO ignore the whitespace rows

            T item;
            try {
                item = ctor.newInstance();
            } catch (Throwable cause) {
                throw new RuntimeException(clazz.getSimpleName() + " new instance failure", cause);
            }

            for (int i = 0; i < fields.length; i++) {
                int columnIndex = i;
                Column column = columns[i];
                if (column != null) {
                    columnIndex = column.index();
                    // auto discover
                    if (columnIndex < 0 && ObjectUtil.hasText(column.title())) {
                        Iterator<Cell> it = headerRow.cellIterator();
                        while (it.hasNext()) {
                            Cell cell = it.next();
                            String cv = cell.getStringCellValue();
                            if (column.title().equals(cv)) {
                                columnIndex = cell.getColumnIndex();
                                break;
                            }
                        }

                        // check again
                        if (columnIndex < 0) {
                            throw new RuntimeException("please set the index or autoIndex by @Column");
                        }
                    }
                }

                // get the corresponding cell value
                Object value = getCellValue(row, columnIndex);

                if (converter != null) {
                    value = converter.convert(rowIndex, columnIndex, value);
                }

                if (value == null) {
                    continue;
                }

                // set the value to corresponding field
                Field field = fields[i];
                Class<?> type = field.getType();
                if (int.class.isAssignableFrom(type)) {
                    int v = ((Double) value).intValue();
                    ReflectionUtil.setField(field, item, v);
                } else if (float.class.isAssignableFrom(type)) {
                    float v = ((Double) value).floatValue();
                    ReflectionUtil.setField(field, item, v);
                } else {
                    ReflectionUtil.setField(field, item, value);
                }
            }

            items.add(item);
        }

        return items;
    }

    public static <T> Workbook write(List<T> items) {
        return write(items, "sheet0", false);
    }

    public static <T> Workbook write(List<T> items, String sheetName, boolean overwrite) {
        if (ObjectUtil.isEmpty(items)) {
            throw new IllegalArgumentException("The items is null or empty");
        }

        Class<?> genericClass = items.get(0).getClass();
        Field[] fields = ReflectionUtil.getFields(genericClass);
        Column[] columns = new Column[fields.length];
        // cache the @Column annotation
        for (int i = 0; i < fields.length; i++) {
            if (columns[i] == null) {
                Field field = fields[i];
                if (field.isAnnotationPresent(Column.class)) {
                    columns[i] = field.getAnnotation(Column.class);
                }
            }
        }

        // new work boot
        Workbook workbook = new XSSFWorkbook();

        // new sheet
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        } else if (!overwrite) {
            sheet = workbook.createSheet();
        }

        CellStyle titleStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        applyCellStyle(titleStyle, font);

        // cache cell style
        Map<Integer, CellStyle> cellStyleMap = new HashMap<>();

        Row titleRow = sheet.createRow(0);
        int rowIndex = 1;
        for (T item : items) {
            Row row = sheet.createRow(rowIndex);
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];

                int index = i;
                Column column = columns[i];
                if (column != null) {
                    if (column.ignore()) {
                        continue;
                    }
                    index = column.index();

                    if (index < 0) {
                        throw new RuntimeException("The excel cell index cannot be less then '0'");
                    }
                }

                // create title row & building cache when it's the first row
                if (rowIndex == 1) {
                    String title = field.getName();
                    if (column != null) {
                        if (ObjectUtil.hasText(column.title())) {
                            title = column.title();
                        }

                        if (ObjectUtil.hasText(column.format())) {
                            try {
                                CellStyle cellStyle = workbook.createCellStyle();
                                DataFormat dataFormat = workbook.createDataFormat();
                                cellStyle.setDataFormat(dataFormat.getFormat(column.format()));
                                cellStyleMap.put(index, cellStyle);
                            } catch (Throwable cause) {
                                // the formatter isn't the excel supported formatter
                                System.out.println(cause.getMessage());
                            }
                        }
                    }

                    Cell titleCell = titleRow.createCell(index);
                    titleCell.setCellStyle(titleStyle);
                    titleCell.setCellValue(title);
                }

                // get the corresponding cell value
                ReflectionUtil.makeAccessible(field);
                Object value = ReflectionUtil.getField(field, item);
                if (value == null) {
                    continue;
                }

                Cell cell = row.createCell(index);
                if (cellStyleMap.containsKey(index)) {
                    cell.setCellStyle(cellStyleMap.get(index));
                }

                Class<?> clazz = field.getType();
                if (column != null && StringUtils.hasText(column.format())) {
                    String fv = String.format(column.format(), value);
                    cell.setCellValue(fv);
                } else if (boolean.class.isAssignableFrom(clazz)) {
                    cell.setCellValue((boolean) value);
                } else if (Date.class.isAssignableFrom(clazz)) {
                    cell.setCellValue((Date) value);
                } else if (int.class.isAssignableFrom(clazz)
                        || double.class.isAssignableFrom(clazz)
                        || float.class.isAssignableFrom(clazz)) {
                    cell.setCellValue(Double.parseDouble(value.toString()));
                } else if (String.class.isAssignableFrom(clazz)) {
                    cell.setCellValue((String) value);
                } else {
                    cell.setCellValue(String.valueOf(value));
                }
            }

            rowIndex++;
        }

        // merge cells
        List<Column> mergeColumns = Arrays.stream(columns).filter(Column::allowMerge).collect(Collectors.toList());
        if (!mergeColumns.isEmpty()) {
            // merge cell style
            CellStyle vStyle = workbook.createCellStyle();
            vStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            for (Column column : mergeColumns) {
                Object previous = null;
                int rowspan = 0;
                int row;
                for (row = 1; row < rowIndex; row++) {
                    Object value = getCellValue(sheet.getRow(row), column.index());
                    if (value != null && value.equals(previous)) {
                        rowspan++;
                    } else {
                        if (rowspan > 1) {
                            sheet.getRow(row - rowspan).getCell(column.index()).setCellStyle(vStyle);
                            sheet.addMergedRegion(
                                    new CellRangeAddress(row - rowspan, row - 1, column.index(), column.index()));
                        }
                        rowspan = 1;
                        previous = value;
                    }
                }

                // all rows should to be merged
                if (rowspan > 1) {
                    sheet.getRow(row - rowspan).getCell(column.index()).setCellStyle(vStyle);
                    sheet.addMergedRegion(
                            new CellRangeAddress(row - rowspan, row - 1, column.index(), column.index()));
                }
            }
        }

        // statistics
        if (rowIndex > 1 && genericClass.isAnnotationPresent(Statistics.class)) {
            Statistics stat = genericClass.getAnnotation(Statistics.class);
            Row statRow = sheet.createRow(rowIndex);
            Cell cell = statRow.createCell(0);
            cell.setCellValue(stat.name());
            for (int column : stat.columns()) {
                cell = statRow.createCell(column);
                cell.setCellFormula(String.format("%s(%s:%s)",
                        stat.formula(),
                        getCellPosition(1, column),
                        getCellPosition(rowIndex - 1, column)));
            }
        }

        // freeze
        if (rowIndex > 1 && genericClass.isAnnotationPresent(Freeze.class)) {
            Freeze freeze = genericClass.getAnnotation(Freeze.class);
            sheet.createFreezePane(freeze.colSplit(), freeze.rowSplit(), freeze.leftMostCol(), freeze.topRow());
        }

        // filter
        if (rowIndex > 1 && genericClass.isAnnotationPresent(Filter.class)) {
            Filter filter = genericClass.getAnnotation(Filter.class);
            int lastRow = filter.lastRow();
            if (lastRow == -1) {
                lastRow = rowIndex;
            }
            sheet.setAutoFilter(new CellRangeAddress(filter.firstRow(), lastRow, filter.firstCol(), filter.lastCol()));
        }

        // autosize the all columns
        for (int i = 0; i < fields.length; i++) {
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }

    private static void applyCellStyle(CellStyle cellStyle, Font font) {
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillPattern(FillPatternType.BRICKS);
        cellStyle.setFillBackgroundColor(HSSFColorPredefined.GREY_40_PERCENT.getIndex());
        cellStyle.setFillForegroundColor(HSSFColorPredefined.WHITE.getIndex());

        font.setBold(true);
        cellStyle.setFont(font);
    }

    private static Workbook createWorkbook(String excelFile) throws IOException {
        Path path = Paths.get(excelFile);
        if (!Files.exists(path)) {
            throw new FileNotFoundException(excelFile);
        }

        return createWorkbook(new FileInputStream(excelFile));
    }

    private static Workbook createWorkbook(InputStream inputStream) throws IOException {
        Workbook workbook = WorkbookFactory.create(inputStream);
        formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        return workbook;
    }

    private static String getCellPosition(int row, int col) {
        col = 'A' + col;
        row = row + 1;
        return String.valueOf((char) col) + row;
    }

    private static Object getCellValue(Row row, int index) {
        Cell cell = row.getCell(index);
        if (cell == null) {
            return null;
        }

        return getCellValue(cell);
    }

    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case STRING:
                return cell.getStringCellValue();
            case FORMULA:
                if (formulaEvaluator == null) {
                    return cell.getCellFormula();
                }
                return getCellValue(formulaEvaluator.evaluateInCell(cell));
            case BLANK:
                return "";
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case ERROR:
                return FormulaError.forInt(cell.getErrorCellValue()).getString();
            case _NONE:
            default:
                return null;
        }
    }
}