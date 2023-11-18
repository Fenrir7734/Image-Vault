package com.fenrir.auth.exception.exceptions;

import static com.fenrir.auth.exception.message.ErrorCode.WRONG_CREDENTIALS_ERROR;

public class WrongCredentialsException extends StandardException {
    private static final String LOGIN_REQUEST_EXCEPTION_MSG = "Wrong credentials";

    public WrongCredentialsException() {
        super(LOGIN_REQUEST_EXCEPTION_MSG, WRONG_CREDENTIALS_ERROR);
    }
}
