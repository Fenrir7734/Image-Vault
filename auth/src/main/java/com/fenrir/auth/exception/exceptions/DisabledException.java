package com.fenrir.auth.exception.exceptions;

public class DisabledException extends StandardException {

    public DisabledException(String msg, String errorCode) {
        super(msg, errorCode);
    }
}
