package pl.filipiak.jakub.training.springtestcontainers.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ApiError {

    private String timestamp = LocalDateTime.now().toString();
    private int status;
    private String error;
    private List<String> errors;
    private String message;
    private String path;

    public ApiError(HttpStatus status, String message, List<String> errors, String path) {
        this.status = status.value();
        this.error = status.name();
        this.message = message;
        this.errors = errors;
        this.path = path;
    }
}
