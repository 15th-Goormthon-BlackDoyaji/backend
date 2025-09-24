package com.goormthon.exception.custom;


import com.goormthon.exception.errorcode.ClientErrorCode;

public class GroomClientErrorException extends GroomErrorException {

    public GroomClientErrorException(ClientErrorCode clientErrorCode) {
        super(clientErrorCode.getMessage(), clientErrorCode.getStatus());
    }
}
