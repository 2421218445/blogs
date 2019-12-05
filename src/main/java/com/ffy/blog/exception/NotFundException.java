package com.ffy.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFundException extends RuntimeException {
    public NotFundException() {
    }

    public NotFundException(String message) {
        super(message);
    }

    public NotFundException(String message, Throwable cause) {
        super(message, cause);
    }
}
