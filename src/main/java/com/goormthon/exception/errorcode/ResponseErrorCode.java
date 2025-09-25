package com.goormthon.exception.errorcode;

import org.springframework.http.HttpStatus;

public interface ResponseErrorCode {

    HttpStatus getStatus();

    String getMessage();
}
