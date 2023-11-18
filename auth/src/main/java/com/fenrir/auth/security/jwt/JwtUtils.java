package com.fenrir.auth.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fenrir.auth.entity.UserEntity;
import com.fenrir.auth.security.oauth2.user.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

import static com.fenrir.auth.security.jwt.JwtClaim.EMAIL_CLAIM;
import static com.fenrir.auth.security.jwt.JwtClaim.ENABLED_CLAIM;
import static com.fenrir.auth.security.jwt.JwtClaim.ROLES_CLAIM;
import static com.fenrir.auth.security.jwt.JwtClaim.USER_ID_CLAIM;
import static com.fenrir.auth.security.jwt.JwtClaim.VERIFIED_CLAIM;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private static final String ISSUER = "IV-auth-service";

    private final JwtConfig jwtConfig;

    public String generateAccessToken(UserEntity user) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
        String authorities = user.getRole().getName();

        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(user.getEmail())
                .withClaim(USER_ID_CLAIM.getName(), user.getId())
                .withClaim(EMAIL_CLAIM.getName(), user.getEmail())
                .withClaim(ROLES_CLAIM.getName(), authorities)
                .withClaim(VERIFIED_CLAIM.getName(), user.getVerified())
                .withClaim(ENABLED_CLAIM.getName(), user.getEnabled())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + jwtConfig.getExpiration()))
                .sign(algorithm);
    }

    public String generateAccessToken(UserPrincipal userPrincipal) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
        String authorities = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(userPrincipal.getUsername())
                .withClaim(USER_ID_CLAIM.getName(), userPrincipal.getId())
                .withClaim(EMAIL_CLAIM.getName(), userPrincipal.getUsername())
                .withClaim(ROLES_CLAIM.getName(), authorities)
                .withClaim(VERIFIED_CLAIM.getName(), userPrincipal.isVerified())
                .withClaim(ENABLED_CLAIM.getName(), userPrincipal.isEnabled())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + jwtConfig.getExpiration()))
                .sign(algorithm);
    }
}