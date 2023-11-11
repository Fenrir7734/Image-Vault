package com.fenrir.auth.exception.exceptions;

import static com.fenrir.auth.exception.message.ErrorCode.DUPLICATE_CREDENTIALS_ERROR;

public class DuplicateCredentialsException extends StandardException {
    private static final String DUPLICATE_CREDENTIALS_MSG = "User with this credentials already exists";

    public DuplicateCredentialsException() {
        super(DUPLICATE_CREDENTIALS_MSG, DUPLICATE_CREDENTIALS_ERROR);
    }
}
