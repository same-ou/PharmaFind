package com.ensam.pharmafind.service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;
    final private String bucketName;

   public String uploadFile(MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
       String filename = UUID.randomUUID().toString();
       minioClient.putObject(
               PutObjectArgs.builder().bucket(bucketName).object(filename)
               .stream(
                       file.getInputStream(),
                       file.getSize(),
                       -1)
               .contentType(file.getContentType())
               .build()
       );
         return filename;
   }

    public String getFileUrl(String filename) throws IOException, InvalidKeyException, NoSuchAlgorithmException, MinioException {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(bucketName)
                        .object(filename)
                        .method(Method.GET)
                        .build()
        );
    }
}
