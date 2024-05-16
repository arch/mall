/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.dao;

import com.mall.model.CmsPreferenceAreaProductRelation;
import com.mall.model.CmsSubjectProductRelation;
import com.mall.model.PmsProductAttributeValue;
import com.mall.model.PmsProductVerifyRecord;
import com.mall.pms.dto.ProductCategoryTreeDto;
import com.mall.pms.dto.ProductEditDto;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductDao {
    List<ProductCategoryTreeDto> selectCategoryTree();

    ProductEditDto getEdit(@Param("id") Long id);

    int insertAttributeValues(@Param("list")List<PmsProductAttributeValue> attributeValues);

    int insertSubjectRelation(@Param("list") List<CmsSubjectProductRelation> subjectProductRelations);

    int insertPreferenceAreaRelation(@Param("list") List<CmsPreferenceAreaProductRelation> preferenceAreaProductRelations);

    int insertVerifyRecord(@Param("list") List<PmsProductVerifyRecord> list);
}