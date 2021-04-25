package com.hussard.hsauth.application.service;

import com.hussard.hsauth.application.model.SecuredResourceResponse;
import com.hussard.hsauth.application.model.UserResponse;
import com.hussard.hsauth.domain.entity.Authority;
import com.hussard.hsauth.domain.enums.AuthorityType;
import com.hussard.hsauth.domain.service.AuthorityDomainService;
import com.hussard.hsauth.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserDomainService userDomainService;
    private final AuthorityDomainService authorityDomainService;

    @Transactional
    public List<UserResponse> admins() {
        Authority authority = authorityDomainService.get(AuthorityType.SUPER);
        Set<Authority> authorities = new HashSet(Arrays.asList(authority));
        return userDomainService.findByAuthorities(authorities)
                .stream().map(UserResponse::of)
                .collect(Collectors.toList());
    }

    public Page<SecuredResourceResponse> resources(Pageable pageable) {
        return null;
    }
}
