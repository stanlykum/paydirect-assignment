package com.paydirect.pokedex.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * Global exception handler class
 *
 * @author Stanly
 */
@RestControllerAdvice
@RestController
@Slf4j
public class ImmediateExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({HttpClientErrorException.class, ResourceNotFoundException.class})
    public ResponseEntity<APIError> handleException(RuntimeException exception) {
        APIError error = null;
        HttpStatus status = null;
        if (exception instanceof ResourceNotFoundException) {
            error = getAPIError(exception, HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
            status = HttpStatus.NOT_FOUND;
        }

        if (exception instanceof HttpClientErrorException.TooManyRequests) {
            error = getAPIError(exception, HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase(), HttpStatus.TOO_MANY_REQUESTS.value());
            status = HttpStatus.TOO_MANY_REQUESTS;
        }
        if (exception instanceof HttpClientErrorException.NotFound) {
            error = getAPIError(exception, HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<APIError>(error, status);
    }

    private APIError getAPIError(RuntimeException exception, String error, int status) {
        APIError errorBuilder = APIError.builder().error(error).message(exception.getMessage()).status(status).timestamp(LocalDateTime.now()).build();
        log.info("Exception occurred error={},message={},status={},timestamp={}", errorBuilder.getError(), errorBuilder.getMessage(), errorBuilder.getStatus(), errorBuilder.getTimestamp());
        return errorBuilder;

    }
}
