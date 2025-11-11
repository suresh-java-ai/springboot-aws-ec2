package com.example.aws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsS3Config {

    private static final String AWS_REGION = "ap-south-1"; // ✅ you can also move this to application.properties

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of(AWS_REGION))
                .credentialsProvider(DefaultCredentialsProvider.create()) // ✅ automatically finds creds
                .build();
    }
}
