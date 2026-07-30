package com.kien.quanlynhahang.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handleBusinessException(BusinessException ex) {

        ApiError error = new ApiError(
                ex.getStatus(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(ex.getStatus())
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) {

        ApiError error = new ApiError(
                500,
                "Đã xảy ra lỗi trong hệ thống",
                LocalDateTime.now()
        );

        return ResponseEntity
                .internalServerError()
                .body(error);
    }
}