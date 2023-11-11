package com.fenrir.core.common.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorMessage {
    private final int statusCode;
    private final LocalDateTime timestamp;
    private final String message;
    private final String description;
}
