package com.kien.quanlynhahang.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Dữ liệu không hợp lệ");

        ApiError error = new ApiError(
                400,
                message,
                LocalDateTime.now()
        );

        return ResponseEntity
                .badRequest()
                .body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleBodyReadException(HttpMessageNotReadableException ex) {

        ApiError error = new ApiError(
                400,
                "Dữ liệu gửi lên không đúng định dạng JSON",
                LocalDateTime.now()
        );

        return ResponseEntity
                .badRequest()
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) {
        ex.printStackTrace();   // <-- thêm dòng này
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
