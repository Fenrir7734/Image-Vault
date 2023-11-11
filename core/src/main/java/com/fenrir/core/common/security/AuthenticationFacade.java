package com.fenrir.core.common.security;

import com.fenrir.core.domain.enums.Role;
import com.fenrir.core.common.exception.exceptions.ForbiddenException;
import com.fenrir.core.security.user.UserPrincipal;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.LongSupplier;

@Service
public class AuthenticationFacade {
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId() {
        return getUserPrincipal().getId();
    }

    public boolean isAuthenticated() {
        Authentication authentication = getAuthentication();
        return authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }

    public boolean isAuthenticated(Long userId) {
        return isAuthenticated() && isUserAuthenticated(userId);
    }

    public boolean isNotAuthenticated(Long userId) {
        return isAuthenticated() && !isUserAuthenticated(userId);
    }

    private boolean isUserAuthenticated(Long userId) {
        return getUserPrincipal().getId().equals(userId);
    }

    public boolean isAuthorized() {
        return isAuthenticated() && getAuthentication().getAuthorities() != null;
    }

    private UserPrincipal getUserPrincipal() {
        Authentication authentication = getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }

    public boolean isAdmin() {
        return isAuthenticated() && hasRole(Role.ADMIN.getName());
    }

    private boolean hasRole(String role) {
        return getUserPrincipal()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> authority.equals(role));
    }

    public List<Role> getRoles() {
        return Role.from(getAuthentication().getAuthorities());
    }

    public void assertOwnership(LongSupplier ownerIdSupplier) {
        if (isNotOwner(ownerIdSupplier)) {
            throw new ForbiddenException();
        }
    }

    private boolean isOwner(LongSupplier ownerIdSupplier) {
        final Long ownerId = ownerIdSupplier.getAsLong();
        return getUserId().equals(ownerId);
    }

    private boolean isNotOwner(LongSupplier ownerIdSupplier) {
        return !isOwner(ownerIdSupplier);
    }
}
