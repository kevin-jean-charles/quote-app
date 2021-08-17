package com.kcode.quoteapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuoteNotFound extends RuntimeException {
    public QuoteNotFound() {
        super();
    }

    public QuoteNotFound(String message) {
        super(message);
    }

    public QuoteNotFound(String message, Throwable cause) {
        super(message, cause);
    }

}


