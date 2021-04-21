package com.hussard.hsauth.application.model;

import com.hussard.hsauth.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {

    private final long id;
    private final  String username;
    private final String accessToken;

    public static AuthResponse of(User user, String accessToken) {
        return AuthResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .accessToken(accessToken)
                .build();
    }

}
