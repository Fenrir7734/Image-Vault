package com.fenrir.auth.exception.message;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCode {
    public static final String FORBIDDEN_ERROR = "AUTH-1000";
    public static final String ACCOUNT_UNVERIFIED_ERROR = "AUTH-1002";
    public static final String ACCOUNT_DISABLED_ERROR = "AUTH-1003";
    public static final String UNKNOWN_OAUTH_PROVIDER_ERROR = "AUTH-1004";
    public static final String EMAIL_USED_WITH_DIFFERENT_OAUTH_PROVIDER = "AUTH-1005";
    public static final String VALIDATION_ERROR = "AUTH-1006";

    public static final String INTERNAL_SERVER_ERROR = "AUTH-9999";
}
