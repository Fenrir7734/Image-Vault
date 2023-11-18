package com.fenrir.auth.exception.exceptions;

import static com.fenrir.auth.exception.message.ErrorCode.FORBIDDEN_ERROR;

public class ForbiddenException extends StandardException {
    private static final String FORBIDDEN_MSG = "Forbidden";

    public ForbiddenException(String msg, String errorCode) {
        super(msg, errorCode);
    }

    public ForbiddenException() {
        super(FORBIDDEN_MSG, FORBIDDEN_ERROR);
    }
}
