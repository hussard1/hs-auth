package com.hussard.hsauth.domain.service;

import com.hussard.hsauth.domain.entity.Authority;
import com.hussard.hsauth.domain.enums.AuthorityType;
import com.hussard.hsauth.domain.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityDomainService {

    private final AuthorityRepository authorityRepository;

    public Authority get(AuthorityType authorityType) {
        return authorityRepository.findByAuthorityType(authorityType)
                .orElseThrow(() -> new IllegalArgumentException("authorityType가 존재하지 않습니다" + authorityType.name()));
    }
}
