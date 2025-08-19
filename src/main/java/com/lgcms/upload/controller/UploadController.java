package com.lgcms.upload.controller;

import com.lgcms.upload.common.dto.BaseResponse;
import com.lgcms.upload.dto.request.LectureUploadRequest;
import com.lgcms.upload.dto.response.LectureUploadResponse;
import com.lgcms.upload.service.LectureUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final LectureUploadService lectureUploadService;

    @PostMapping("/lecture")
    public ResponseEntity<BaseResponse<LectureUploadResponse>> uploadOriginFiles(
            @RequestPart(name = "files") List<MultipartFile> files,
            @RequestPart(name = "id", required = false) String lectureId,
            @RequestHeader("X-USER-ID") String memberId) {

            lectureUploadService.uploadFiles(files, lectureId);
        return ResponseEntity.ok(BaseResponse.ok(null));
    }
}
