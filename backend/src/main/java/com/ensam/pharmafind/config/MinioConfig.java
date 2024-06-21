package com.ensam.pharmafind.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Value("${application.storage.minio.url}")
    private String minioUrl;
    @Value("${application.storage.minio.accessKey}")
    private String accessKey;
    @Value("${application.storage.minio.secretKey}")
    private String secretKey;
    @Value("${application.storage.minio.bucket-name}")
    private String bucketName;
    @Value("${application.storage.minio.secure}")
    private Boolean minioSecure;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioUrl, 9000,minioSecure)
                .credentials(accessKey, secretKey)
                .build();
    }
}
