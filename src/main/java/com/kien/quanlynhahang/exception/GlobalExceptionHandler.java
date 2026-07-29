package com.kien.quanlynhahang.exception;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import tools.jackson.core.exc.StreamReadException;

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

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> xuLySaiDangNhap(BadCredentialsException ex) {
        ApiError error = new ApiError(401, "Tên đăng nhập hoặc mật khẩu không đúng", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> xuLyBodyKhongHopLe(HttpMessageNotReadableException ex) {
        ApiError error = new ApiError(400, "Body JSON không hợp lệ", LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(StreamReadException.class)
    public ResponseEntity<ApiError> xuLyJsonKhongHopLe(StreamReadException ex) {
        ApiError error = new ApiError(400, "Dữ liệu món ăn không đúng định dạng JSON", LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> xuLySaiMethod(HttpRequestMethodNotSupportedException ex) {
        ApiError error = new ApiError(405, "Sai phương thức HTTP", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    @ExceptionHandler(UploadFileException.class)
    public ResponseEntity<ApiError> handleUploadFileException(
            UploadFileException ex) {

        ApiError error = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.badRequest().body(error);
    }
}
