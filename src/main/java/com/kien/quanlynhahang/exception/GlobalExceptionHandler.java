package com.kien.quanlynhahang.exception;

import com.kien.quanlynhahang.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> xuLyLoiNghiepVu(BusinessException ex) {

        if (ex.getStatus() >= 500) {
            log.error("Lỗi nghiệp vụ phía server: status={}, message={}",
                    ex.getStatus(),
                    ex.getMessage(),
                    ex);
        } else {
            log.warn("Lỗi nghiệp vụ: status={}, message={}",
                    ex.getStatus(),
                    ex.getMessage());
        }

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .status(ex.getStatus())
                .message(ex.getMessage())
                .build();

        return ResponseEntity
                .status(ex.getStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> xuLyLoiValidate(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Dữ liệu không hợp lệ");

        log.warn("Lỗi validate request: {}", message);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .status(400)
                .message(message)
                .build();

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> xuLyLoiDocRequest(
            HttpMessageNotReadableException ex) {

        log.warn("Request JSON không đúng định dạng: {}", ex.getMessage());

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .status(400)
                .message("Dữ liệu gửi lên không đúng định dạng JSON")
                .build();

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> xuLyLoiHeThong(Exception ex) {
        ex.printStackTrace();
        log.error("Lỗi hệ thống chưa xử lý", ex);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(false)
                .status(500)
                .message("Đã xảy ra lỗi trong hệ thống")
                .build();

        return ResponseEntity
                .internalServerError()
                .body(response);
    }
}
