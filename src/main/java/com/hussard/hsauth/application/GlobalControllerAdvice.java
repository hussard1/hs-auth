package com.hussard.hsauth.application;

import com.hussard.hsauth.application.model.CommonErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.hussard.hsauth.application.GlobalControllerAdvice.ORDER;

@Slf4j
@Order(ORDER)
@RestControllerAdvice(annotations = RestController.class)
public class GlobalControllerAdvice {

    public static final int ORDER = 0;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public CommonErrorResponse handle(RuntimeException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return CommonErrorResponse.builder()
                .path(request.getRequestURI())
                .errorCode(e.getClass().getSimpleName())
                .message(e.getMessage())
                .build();
    }
}
