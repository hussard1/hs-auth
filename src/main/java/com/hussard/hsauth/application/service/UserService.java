package com.hussard.hsauth.application.service;

import com.hussard.hsauth.application.exception.DuplicateUserNameException;
import com.hussard.hsauth.application.model.UserRequest;
import com.hussard.hsauth.application.model.UserResponse;
import com.hussard.hsauth.domain.entity.User;
import com.hussard.hsauth.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDomainService userDomainService;

    public UserResponse getUser(final long id) {
        return userDomainService.getUser(id)
                .map(UserResponse::of)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 유저 아이디 입니다."));
    }

}
