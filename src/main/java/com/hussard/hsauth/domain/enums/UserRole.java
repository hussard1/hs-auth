package com.hussard.hsauth.domain.enums;

import com.hussard.hsauth.domain.exception.UserRoleNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    NORMAL("일반 유저"),
    SUPER("슈퍼 유저"),
    ;

    private final String comment;

    public static UserRole of(final String s) {
        return Arrays.stream(UserRole.values()).filter(userRole -> userRole.name().equals(s))
                .findAny().orElseThrow(() -> new UserRoleNotFoundException("유저가 잘못된 권한을 가지고 있습니다" + s));
    }
}
