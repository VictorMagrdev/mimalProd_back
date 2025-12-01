package com.example.minimal_prod_backend.service.client;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URI;
import java.time.Duration;
import java.util.List;

public class CloudflareR2Client {

    private final S3Client s3Client;
    private final S3Presigner presigner;
    private final String endpoint;

    public CloudflareR2Client(String endpoint, String accessKey, String secretKey, String bucket) {
        this.endpoint = endpoint;

        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        S3Configuration s3Config = S3Configuration.builder()
                .pathStyleAccessEnabled(true)
                .build();

        this.s3Client = S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.of("auto"))
                .serviceConfiguration(s3Config)
                .build();

        this.presigner = S3Presigner.builder()
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(Region.of("auto"))
                .serviceConfiguration(s3Config)
                .build();
    }

    public List<Bucket> listBuckets() {
        return s3Client.listBuckets().buckets();
    }

    public List<S3Object> listObjects(String bucket) {
        ListObjectsV2Request req = ListObjectsV2Request.builder()
                .bucket(bucket)
                .build();
        return s3Client.listObjectsV2(req).contents();
    }

    public String upload(String bucket, String key, byte[] data) {
        s3Client.putObject(
                PutObjectRequest.builder().bucket(bucket).key(key).build(),
                software.amazon.awssdk.core.sync.RequestBody.fromBytes(data)
        );
        return String.format("%s/%s/%s", endpoint, bucket, key);
    }

    public String generatePresignedUploadUrl(String bucket, String key, Duration expiration) {
        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(expiration)
                .putObjectRequest(r -> r.bucket(bucket).key(key))
                .build();
        PresignedPutObjectRequest presigned = presigner.presignPutObject(presignRequest);
        return presigned.url().toString();
    }
}
