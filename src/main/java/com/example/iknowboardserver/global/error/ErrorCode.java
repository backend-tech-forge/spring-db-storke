package com.example.iknowboardserver.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    /**
     * 400
     */
    BAD_REQUEST(400, "잘못된 요청입니다"),

    /**
     * 401
     */
    UNAUTHORIZED(401, "인증되지 않은 사용자입니다"),

    /**
     * 403
     */
    FORBIDDEN(403, "권한이 없는 사용자입니다"),

    /**
     * 500
     */
    SERVER_DEFAULT_ERROR(500, "서버 내부오류"),
    ;

    private final int httpStatus;
    private final String message;

    ErrorCode(int httpStatus, String s) {
        this.httpStatus = httpStatus;
        this.message = s;
    }
}
