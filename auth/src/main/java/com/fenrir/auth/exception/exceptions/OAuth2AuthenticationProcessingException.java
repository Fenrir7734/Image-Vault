package com.fenrir.auth.exception.exceptions;

public class OAuth2AuthenticationProcessingException extends StandardException {

    public OAuth2AuthenticationProcessingException(String msg, String errorCode) {
        super(msg, errorCode);
    }
}