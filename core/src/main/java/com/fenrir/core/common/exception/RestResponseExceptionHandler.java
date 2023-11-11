package com.fenrir.core.common.exception;

import com.fenrir.core.common.exception.exceptions.ForbiddenException;
import com.fenrir.core.common.exception.exceptions.InternalServerError;
import com.fenrir.core.common.exception.exceptions.NotFoundException;
import com.fenrir.core.common.exception.message.ConstraintViolationErrorMessage;
import com.fenrir.core.common.exception.message.ConstraintViolationInfo;
import com.fenrir.core.common.exception.message.ErrorMessage;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<ErrorMessage> handleNotFoundException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ ForbiddenException.class })
    public ResponseEntity<ErrorMessage> handleForbiddenException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
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
                request.getDescription(false),
                constraintViolations
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ Exception.class, InternalServerError.class })
    public ResponseEntity<ErrorMessage> handleUnknownException(Exception ex, WebRequest request) {
        ex.printStackTrace();

        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                INTERNAL_SERVER_ERROR_MESSAGE,
                request.getDescription(false)
        );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
