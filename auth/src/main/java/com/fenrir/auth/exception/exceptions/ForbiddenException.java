package com.fenrir.auth.exception.exceptions;

public class ForbiddenException extends StandardException {

    public ForbiddenException(String msg, String errorCode) {
        super(msg, errorCode);
    }
}
