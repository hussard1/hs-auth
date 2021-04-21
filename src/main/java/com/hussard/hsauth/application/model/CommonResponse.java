package com.hussard.hsauth.application.model;

import com.hussard.hsauth.application.enums.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class CommonResponse<T> {
    private final int status;
    private final T data;
    private final ErrorCode errorCode;

    public static <T> CommonResponse<T> of(T data) {
        return CommonResponse.<T>builder()
                .status(HttpStatus.OK.value())
                .data(data)
                .build();
    }

    public static <T> CommonResponse<T> of400Error(ErrorCode errorCode, T data) {
        return CommonResponse.<T>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errorCode(errorCode)
                .data(data)
                .build();
    }
}
