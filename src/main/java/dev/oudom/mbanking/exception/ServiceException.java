package dev.oudom.mbanking.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ServiceException {

    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<ApiResponseStatus> handleServiceErrors(
            ResponseStatusException e,
            HttpServletRequest request
    ) {

        HttpStatus status = (HttpStatus) e.getStatusCode();
        ApiResponseStatus body = new ApiResponseStatus(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                e.getReason(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(e.getStatusCode())
                .body(body);
    }

}
