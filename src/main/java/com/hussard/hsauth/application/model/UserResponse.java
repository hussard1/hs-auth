package com.hussard.hsauth.application.model;

import com.hussard.hsauth.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Builder
@ToString
public class UserResponse {

    private final long id;
    private final String username;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}