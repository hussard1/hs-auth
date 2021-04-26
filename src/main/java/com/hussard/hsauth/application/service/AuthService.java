package com.hussard.hsauth.application.service;

import com.hussard.hsauth.application.exception.DuplicateUserNameException;
import com.hussard.hsauth.application.exception.InvalidPasswordException;
import com.hussard.hsauth.application.model.AuthResponse;
import com.hussard.hsauth.application.model.UserRequest;
import com.hussard.hsauth.config.security.jwt.JwtTokenProvider;
import com.hussard.hsauth.domain.entity.User;
import com.hussard.hsauth.domain.enums.AuthorityType;
import com.hussard.hsauth.domain.service.AuthorityDomainService;
import com.hussard.hsauth.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserDomainService userDomainService;
    private final AuthorityDomainService authorityDomainService;

    @Transactional
    public AuthResponse signin(UserRequest userRequest) {
        User user = userDomainService.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("유저 이름이 존재하지 않습니다."));

        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword()))
            throw new InvalidPasswordException("비밀번호가 잘못되었습니다.");

        String token = jwtTokenProvider.createToken(user);

        return AuthResponse.of(user, token);

    }

    @Transactional
    public AuthResponse signup(UserRequest userRequest) {
        if (userDomainService.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new DuplicateUserNameException("유저 이름이 이미 존재합니다.");
        }
        User user = userDomainService.save(userRequest.toEntity());
        user.updatePassword(passwordEncoder.encode(user.getPassword()));
        user.addAuthority(authorityDomainService.get(AuthorityType.NORMAL));

        String token = jwtTokenProvider.createToken(user);

        return AuthResponse.of(user, token);
    }
}
