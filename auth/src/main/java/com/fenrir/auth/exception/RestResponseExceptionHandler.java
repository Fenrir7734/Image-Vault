package com.fenrir.auth.exception;

import com.fenrir.auth.exception.exceptions.DisabledException;
import com.fenrir.auth.exception.exceptions.OAuth2AuthenticationProcessingException;
import com.fenrir.auth.exception.exceptions.StandardException;
import com.fenrir.auth.exception.exceptions.UnverifiedException;
import com.fenrir.auth.exception.message.ErrorCode;
import com.fenrir.auth.exception.message.ErrorMessage;
import jakarta.ws.rs.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestResponseExceptionHandler {
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error";

    @ExceptionHandler({ ForbiddenException.class, AccessDeniedException.class })
    public ResponseEntity<ErrorMessage> handleForbiddenException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                ErrorCode.FORBIDDEN_ERROR
        );
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ UnverifiedException.class, DisabledException.class })
    public ResponseEntity<ErrorMessage> handleAccountBlockedException(StandardException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getErrorCode()
        );
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ OAuth2AuthenticationProcessingException.class })
    public ResponseEntity<ErrorMessage> handleOAuthProcessingException(StandardException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getErrorCode()
        );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorMessage> handleUnknownException(Exception ex, WebRequest request) {
        ex.printStackTrace();

        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                INTERNAL_SERVER_ERROR_MESSAGE,
                ErrorCode.INTERNAL_SERVER_ERROR
        );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
