package com.lgcms.upload.common.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UploadError implements ErrorCodeInterface {
    UPLOAD_FORBIDDEN("ULDE-01","업로드 서버 접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    UPLOAD_FAIL("ULDE-02","파일 업로드가 실패했습니다",HttpStatus.INTERNAL_SERVER_ERROR)
    ;

    private final String status;
    private final String message;
    private final HttpStatus httpStatus;

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.builder()
            .status(status)
            .message(message)
            .httpStatus(httpStatus)
            .build();
    }
}
