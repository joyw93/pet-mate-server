package com.petmate.demo.common.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.petmate.demo.common.service.CommonService;
import com.petmate.demo.community.model.CommunityPostImage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommonServiceImpl implements CommonService {
    private final AmazonS3 amazonS3;
    @Value("${aws.bucketName}")
    private String bucketName;
    @Value("${aws.bucketPath}")
    private String bucketPath;
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    @Override
    public List<String> uploadFile(List<MultipartFile> files) throws IOException {
        List<String> imgUrls = new ArrayList<String>();
        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) {
                throw new IllegalArgumentException("File is empty: " + multipartFile.getOriginalFilename());
            }
            if (multipartFile.getSize() > MAX_FILE_SIZE) {
                throw new IllegalArgumentException("File size exceeds the maximum allowed limit: " + multipartFile.getOriginalFilename());
            }
            CommunityPostImage postImage = new CommunityPostImage();
            String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
            String key = bucketPath + s3FileName;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(multipartFile.getSize());
            metadata.setContentType(multipartFile.getContentType());
            try {
                amazonS3.putObject(bucketName, key, multipartFile.getInputStream(), metadata);
                String imgUrl = amazonS3.getUrl(bucketName, key).toString();
                imgUrls.add(imgUrl);
            } catch (AmazonS3Exception e) {
                throw new IOException("Failed to upload file: " + multipartFile.getOriginalFilename(), e);
            }
        }
        return imgUrls;
    }
}
