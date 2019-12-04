package pl.filipiak.jakub.training.springtestcontainers.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleConstraintViolation(ResourceNotFoundException ex,
                                                            WebRequest request) {
        List<String> errors = new ArrayList<>();
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ex.getLocalizedMessage(),
                errors,
                ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity<>(
                apiError,
                new HttpHeaders(),
                HttpStatus.valueOf(apiError.getStatus()));
    }
}
