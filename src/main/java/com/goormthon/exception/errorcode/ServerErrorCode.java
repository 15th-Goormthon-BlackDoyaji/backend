package com.goormthon.exception.errorcode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ServerErrorCode implements ResponseErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다. 관리자에게 문의하세요."),
    INVALID_USER_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "userId를 찾을 수 없습니다"),
    RESPONSE_PARSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ai 응답을 바인딩 할 수 없습니다");

    private final HttpStatus status;
    private final String message;

    ServerErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
