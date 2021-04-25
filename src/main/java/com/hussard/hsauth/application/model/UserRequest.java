package com.hussard.hsauth.application.model;

import com.hussard.hsauth.domain.entity.Authority;
import com.hussard.hsauth.domain.entity.User;
import com.hussard.hsauth.domain.enums.AuthorityType;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class UserRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
