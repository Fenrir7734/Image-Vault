package com.fenrir.auth.exception.exceptions;

public class UnverifiedException extends StandardException {

    public UnverifiedException(String msg, String errorCode) {
        super(msg, errorCode);
    }
}
