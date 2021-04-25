package com.hussard.hsauth.config;

import com.hussard.hsauth.domain.service.SecuredResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final SecuredResourceService securedResourceService;
    private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

    public CustomFilterInvocationSecurityMetadataSource(SecuredResourceService securedResourceService) {
        this.securedResourceService = securedResourceService;
        this.requestMap = securedResourceService.getMetaDataSource();
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {

        Set<ConfigAttribute> allAttributes = new HashSet<>();

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
                .entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    public Collection<ConfigAttribute> getAttributes(Object object) {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
                .entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public void reload() {
        requestMap.clear();

        Map<RequestMatcher, Collection<ConfigAttribute>> reloadedMap;

        if (securedResourceService != null) {
            reloadedMap = securedResourceService.getMetaDataSource();
            if (!reloadedMap.isEmpty()) requestMap.putAll(reloadedMap);
        }

        log.info("Secured Url Resources - Role Mappings reloaded at Runtime!");
    }
}
