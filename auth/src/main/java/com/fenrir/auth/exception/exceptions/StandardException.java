package com.fenrir.auth.exception.exceptions;

import lombok.Getter;

@Getter
public class StandardException extends RuntimeException {
    private final String errorCode;

    protected StandardException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}
