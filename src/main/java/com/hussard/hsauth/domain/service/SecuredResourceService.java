package com.hussard.hsauth.domain.service;

import com.hussard.hsauth.domain.entity.Authority;
import com.hussard.hsauth.domain.entity.SecuredResource;
import com.hussard.hsauth.domain.repository.SecuredResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SecuredResourceService {

    private final SecuredResourceRepository securedResourceRepository;

    public Map<RequestMatcher, Collection<ConfigAttribute>> getMetaDataSource() {

        Map<RequestMatcher, Collection<ConfigAttribute>> resultMap = new LinkedHashMap<>();

        List<SecuredResource> securedResources = securedResourceRepository.findAll();

        for (SecuredResource securedResource : securedResources) {
            List<ConfigAttribute> configs = new LinkedList<>();
            for (Authority authority : securedResource.getAuthorities()) {
                configs.add(new SecurityConfig(authority.getAuthority()));
            }

            if (CollectionUtils.isEmpty(securedResource.getHttpMethods())) {
                resultMap.put(new AntPathRequestMatcher(securedResource.getPattern()), configs);
            } else {
                for (HttpMethod httpMethod : securedResource.getHttpMethods()) {
                    resultMap.put(new AntPathRequestMatcher(securedResource.getPattern(), httpMethod.name()), configs);
                }
            }
        }

        return resultMap;
    }
}
