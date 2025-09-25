package com.goormthon.exception.custom;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class GroomErrorException extends RuntimeException {

    private final HttpStatus httpStatus;

    protected GroomErrorException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
