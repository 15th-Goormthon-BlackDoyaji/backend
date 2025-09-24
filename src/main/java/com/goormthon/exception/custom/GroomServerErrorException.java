package com.goormthon.exception.custom;

import com.goormthon.exception.errorcode.ServerErrorCode;

public class GroomServerErrorException extends GroomErrorException {

    public GroomServerErrorException(ServerErrorCode serverErrorCode) {
        super(serverErrorCode.getMessage(), serverErrorCode.getStatus());
    }
}
