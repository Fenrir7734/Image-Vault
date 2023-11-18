package com.fenrir.auth.exception.exceptions;

import static com.fenrir.auth.exception.message.ErrorCode.PASSWORD_MISMATCH_ERROR;

public class PasswordMismatchException extends StandardException {
    private static final String PASSWORD_MISMATCH_MSG = "Password mismatch";

    public PasswordMismatchException() {
        super(PASSWORD_MISMATCH_MSG, PASSWORD_MISMATCH_ERROR);
    }
}
