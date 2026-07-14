package com.kien.quanlynhahang.exception;
import com.kien.quanlynhahang.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(KhongTimThayException.class)
    public ResponseEntity<ApiError> xuLyKhongTimThay(KhongTimThayException ex) {
        ApiError error = new ApiError(404,ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NghiepVuException.class)
    public ResponseEntity<ApiError> xuLyNghiepVu(NghiepVuException ex) {
        ApiError error = new ApiError(400, ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> xuLyValidation(
            MethodArgumentNotValidException ex
    ) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ApiError error = new ApiError(400, message, LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }
}