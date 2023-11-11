package com.fenrir.gateway.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fenrir.gateway.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

import static com.fenrir.gateway.security.jwt.JwtClaim.EMAIL_CLAIM;
import static com.fenrir.gateway.security.jwt.JwtClaim.ENABLED_CLAIM;
import static com.fenrir.gateway.security.jwt.JwtClaim.ROLES_CLAIM;
import static com.fenrir.gateway.security.jwt.JwtClaim.USER_ID_CLAIM;
import static com.fenrir.gateway.security.jwt.JwtClaim.VERIFIED_CLAIM;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtUtils jwtUtils;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .map(auth -> jwtUtils.decodedJwt(auth.getCredentials().toString()))
                .onErrorResume(m -> Mono.empty())
                .map(this::buildUserDetails)
                .map(userDetails -> new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()))
                .switchIfEmpty(Mono.just(UsernamePasswordAuthenticationToken.unauthenticated(null, null)))
                .cast(Authentication.class);
    }

    private UserDetails buildUserDetails(DecodedJWT jwt) {
        final List<GrantedAuthority> roles = jwtUtils.extractClaimList(jwt, ROLES_CLAIM)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return UserPrincipal.builder()
                .id(jwtUtils.extractClaim(jwt, USER_ID_CLAIM, Long.class))
                .email(jwtUtils.extractClaim(jwt, EMAIL_CLAIM, String.class))
                .verified(jwtUtils.extractClaim(jwt, VERIFIED_CLAIM, Boolean.class))
                .enabled(jwtUtils.extractClaim(jwt, ENABLED_CLAIM, Boolean.class))
                .authorities(roles)
                .build();
    }
}
