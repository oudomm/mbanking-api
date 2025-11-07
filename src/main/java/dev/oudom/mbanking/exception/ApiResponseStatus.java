package dev.oudom.mbanking.exception;

import java.time.LocalDateTime;

public record ApiResponseStatus(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {
}
