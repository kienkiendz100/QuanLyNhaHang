package com.kien.quanlynhahang.exception;

import org.springframework.http.HttpStatus;

public class ChuaXacThucException extends BusinessException {

    public ChuaXacThucException(String message) {
        super(HttpStatus.UNAUTHORIZED.value(), message);
    }

}