package com.lgcms.upload.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.lgcms.upload.common.dto.exception.BaseException;
import com.lgcms.upload.common.dto.exception.UploadError;
import com.lgcms.upload.common.kafka.dto.LectureUploadDto;
import com.lgcms.upload.event.producer.UploadEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureUploadService {

    private final AmazonS3 amazonS3;
    private final UploadEvent uploadEvent;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.cdn.url}")
    private String prefix;

    public void uploadFiles(List<MultipartFile> files, String lectureId) {

        LectureUploadDto lectureUploadDto = new LectureUploadDto();
        lectureUploadDto.setLectureId(lectureId);
        try {
            for (MultipartFile file : files) {
                String key = lectureId + "/" + file.getOriginalFilename();

                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(file.getSize());
                metadata.setContentType(file.getContentType());

//                amazonS3.putObject(bucket, key, file.getInputStream(), metadata);

                if ("application/pdf".equalsIgnoreCase(file.getContentType())) {
                    lectureUploadDto.setBookKey(prefix + key);   // 교안
                } else if (file.getContentType() != null && file.getContentType().startsWith("image/")) {
                    lectureUploadDto.setThumbnailKey(prefix + key); // 썸네일
                }
            }
            uploadEvent.lectureUploadEvent(lectureUploadDto);
        } catch (Exception e) {
            log.error("S3 upload failed", e);
            throw new BaseException(UploadError.UPLOAD_FAIL);
        }
    }

    public void uploadVideo(MultipartFile file, String lectureId) {
        try {
            String key = lectureId + "/" + file.getOriginalFilename();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            amazonS3.putObject(bucket, key, file.getInputStream(), metadata);
            System.out.println(key);
        } catch (Exception e) {
            log.error("S3 upload failed", e);
            throw new BaseException(UploadError.UPLOAD_FAIL);
        }
    }
}
