package com.kien.quanlynhahang.exception;

import org.springframework.http.HttpStatus;

public class GuiEmailException extends BusinessException {

    public GuiEmailException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

}