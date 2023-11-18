package com.fenrir.auth.security;

import com.fenrir.auth.exception.exceptions.ForbiddenException;
import com.fenrir.auth.security.oauth2.user.UserPrincipal;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationFacade {

    public void assertAuthenticated() {
        if (!isAuthenticated()) {
            throw new ForbiddenException();
        }
    }

    public void assertNotAuthenticated() {
        if (isAuthenticated()) {
            throw new ForbiddenException();
        }
    }

    public UserPrincipal getUserPrincipal() {
        Authentication authentication = getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private boolean isAuthenticated() {
        Authentication authentication = getAuthentication();
        return authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }
}
