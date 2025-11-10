package com.example.aws;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URL;
import java.util.UUID;

@Service
public class S3Service {
    private S3Client s3Client;
    private final String bucketName = "springboot-aws-ec2-bucket";

    public S3Service(S3Client s3Client){
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) {
        String key = "employee-images/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        try {
            // Upload the file to S3
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .contentType(file.getContentType())
                            .acl(ObjectCannedACL.PUBLIC_READ) // Optional: make file publicly readable
                            .build(),
                    software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes())
            );

            URL url = s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(key));
            return url.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error uploading to S3", e);
        }
    }
}
