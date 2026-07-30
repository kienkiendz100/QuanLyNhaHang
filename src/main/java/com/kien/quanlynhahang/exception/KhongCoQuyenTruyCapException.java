package com.kien.quanlynhahang.exception;

import org.springframework.http.HttpStatus;

public class KhongCoQuyenTruyCapException extends BusinessException {

    public KhongCoQuyenTruyCapException(String message) {
        super(HttpStatus.FORBIDDEN.value(), message);
    }

}