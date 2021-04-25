package com.hussard.hsauth.config;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

public class CustomRoleVoter extends RoleVoter {

    @Override
    public int vote(Authentication authentication, Object object,
                    Collection<ConfigAttribute> attributes) {
        int result = ACCESS_ABSTAIN;

        Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);

        for (ConfigAttribute attribute : attributes) {

            //허용되는 URL 체크
//            if(isAccessPath((FilterInvocation)object)){
//                return ACCESS_GRANTED;
//            }

            if (this.supports(attribute)) {
                result = ACCESS_DENIED;

                // Attempt to find a matching granted authority
                for (GrantedAuthority authority : authorities) {
                    if (attribute.getAttribute().equals(authority.getAuthority())) {
                        return ACCESS_GRANTED;
                    }
                }
            }
        }

        return result;
    }

    Collection<? extends GrantedAuthority> extractAuthorities(
            Authentication authentication) {
        return authentication.getAuthorities();
    }
//
//    boolean isAccessPath(FilterInvocation filterInvocation){
//        String requestPath = (filterInvocation).getRequest().getServletPath();
//        if(requestPath.startsWith("/auth/")
//            || requestPath.startsWith("/")
//            || requestPath.startsWith("/index")
//        ){
//            return true;
//        }
//        return false;
//    }
}
