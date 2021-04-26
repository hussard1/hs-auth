package com.hussard.hsauth.application.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommonErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String path;
    private final String errorCode;
    private final String message;

    public static CommonErrorResponse of(String path, String errorCode, String message) {
        return CommonErrorResponse.builder()
                .path(path)
                .errorCode(errorCode)
                .message(message)
                .build();
    }
}
