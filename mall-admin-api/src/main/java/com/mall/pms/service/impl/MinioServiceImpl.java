/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

package com.mall.pms.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.mall.crosscut.util.Assert;
import com.mall.pms.dto.MinioUploadDto;
import com.mall.pms.service.MinioService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteArgs;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.SetBucketPolicyArgs;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@ConfigurationProperties(prefix = "minio")
public class MinioServiceImpl implements MinioService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String endpoint;
    private String bucket;
    private String accessKey;
    private String secretKey;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public MinioUploadDto upload(MultipartFile file) {
        try {
            //创建一个MinIO的Java客户端
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(accessKey, secretKey)
                    .build();
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (found) {
                logger.info("Minio存储桶已经存在！");
            } else {
                //创建存储桶并设置只读权限
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
                minioClient.setBucketPolicy(
                        SetBucketPolicyArgs.builder()
                                .bucket(bucket)
                                .config(BucketPolicy.readonly(bucket)).build());
            }

            String filename = file.getOriginalFilename();
            // 设置存储对象名称
            String objectName = formatter.format(LocalDate.now()) + "/" + filename;
            // 使用putObject上传一个文件到存储桶中
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MIN_MULTIPART_SIZE).build();
            minioClient.putObject(putObjectArgs);

            logger.info("文件上传成功!" + objectName);

            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName(filename);
            minioUploadDto.setUrl(endpoint + "/" + bucket + "/" + objectName);
            return minioUploadDto;
        } catch (Throwable cause) {
            logger.error("上传文件错误: " + cause.getMessage(), cause);
            Assert.failure(cause.getMessage());
        }
        return null;
    }

    @Override
    public void delete(String name) {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(accessKey, secretKey)
                    .build();
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(name).build());
        } catch (Throwable cause) {
            logger.error("删除文件错误: " + cause.getMessage(), cause);
            Assert.failure(cause.getMessage());
        }
    }

    public static class BucketPolicy {

        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        private String Version;
        private List<Statement> Statement;

        public String getVersion() {
            return Version;
        }

        public void setVersion(String version) {
            Version = version;
        }

        public List<BucketPolicy.Statement> getStatement() {
            return Statement;
        }

        public void setStatement(List<BucketPolicy.Statement> statement) {
            Statement = statement;
        }

        private static class Statement {

            private String Effect;
            private String Principal;
            private String Action;
            private String Resource;

            public String getEffect() {
                return Effect;
            }

            public void setEffect(String effect) {
                Effect = effect;
            }

            public String getPrincipal() {
                return Principal;
            }

            public void setPrincipal(String principal) {
                Principal = principal;
            }

            public String getAction() {
                return Action;
            }

            public void setAction(String action) {
                Action = action;
            }

            public String getResource() {
                return Resource;
            }

            public void setResource(String resource) {
                Resource = resource;
            }
        }

        public static String readonly(String bucket) throws JsonProcessingException {
            BucketPolicy policy = new BucketPolicy();
            policy.setVersion(formatter.format(LocalDate.now()));
            Statement statement = new Statement();
            statement.setEffect("Allow");
            statement.setPrincipal("*");
            statement.setAction("s3:GetObject");
            statement.setResource("arn:aws:s3:::" + bucket + "/*.**");
            policy.setStatement(Collections.singletonList(statement));

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);

            return objectMapper.writeValueAsString(policy);
        }
    }
}