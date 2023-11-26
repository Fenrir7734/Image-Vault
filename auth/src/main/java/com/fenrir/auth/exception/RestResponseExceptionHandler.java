package com.fenrir.auth.exception;

import com.fenrir.auth.exception.exceptions.DisabledException;
import com.fenrir.auth.exception.exceptions.DuplicateCredentialsException;
import com.fenrir.auth.exception.exceptions.OAuth2AuthenticationProcessingException;
import com.fenrir.auth.exception.exceptions.PasswordMismatchException;
import com.fenrir.auth.exception.exceptions.StandardException;
import com.fenrir.auth.exception.exceptions.UnverifiedException;
import com.fenrir.auth.exception.exceptions.WrongCredentialsException;
import com.fenrir.auth.exception.message.ConstraintViolationErrorMessage;
import com.fenrir.auth.exception.message.ConstraintViolationInfo;
import com.fenrir.auth.exception.message.ErrorCode;
import com.fenrir.auth.exception.message.ErrorMessage;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class RestResponseExceptionHandler {
    private static final String CONSTRAINT_VIOLATION_MESSAGE = "Constraint Violation";
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error";

    @ExceptionHandler({ WrongCredentialsException.class })
    public ResponseEntity<ErrorMessage> handleUnauthorizedException(StandardException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getErrorCode()
        );
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

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

    @ExceptionHandler({ PasswordMismatchException.class, DuplicateCredentialsException.class })
    public ResponseEntity<ErrorMessage> handleCredentialsException(StandardException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getErrorCode()
        );
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ ConstraintViolationException.class, MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorMessage> handleConstraintViolationException(Exception ex, WebRequest request) {
        List<ConstraintViolationInfo> constraintViolations = null;

        if (ex instanceof ConstraintViolationException cve) {
            constraintViolations = cve.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolationInfo::from)
                    .toList();
        } else if (ex instanceof MethodArgumentNotValidException manve) {
            constraintViolations = manve.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(ConstraintViolationInfo::from)
                    .toList();
        } else {
            handleUnknownException(ex, request);
        }

        ErrorMessage message = new ConstraintViolationErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                CONSTRAINT_VIOLATION_MESSAGE,
                ErrorCode.VALIDATION_ERROR,
                constraintViolations
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
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
