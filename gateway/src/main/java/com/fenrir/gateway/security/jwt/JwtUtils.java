package com.fenrir.gateway.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class JwtUtils {
    private static final String ISSUER = "IV-auth-service";

    private final JwtConfig jwtConfig;
    private final JWTVerifier verifier;

    public JwtUtils(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.verifier = initValidator();
    }

    public DecodedJWT decodedJwt(String token) {
        return verifier.verify(token);
    }

    public <T> T extractClaim(DecodedJWT jwt, JwtClaim claim, Class<T> clazz) {
        final Claim extractedClaim = jwt.getClaim(claim.getName());

        if (clazz == String.class) {
            return clazz.cast(extractedClaim.asString());
        }
        if (clazz == Long.class) {
            return clazz.cast(extractedClaim.asLong());
        }
        if (clazz == Boolean.class) {
            return clazz.cast(extractedClaim.asBoolean());
        }

        throw new UnsupportedOperationException();
    }

    public List<String> extractClaimList(DecodedJWT jwt, JwtClaim claim) {
        final Claim rolesClaim = jwt.getClaim(claim.getName());
        if (rolesClaim.isMissing() || rolesClaim.isNull()) {
            return Collections.emptyList();
        }
        final List<String> roles = rolesClaim.asList(String.class);
        return roles == null ? Collections.singletonList(rolesClaim.asString()) : roles;
    }

    private JWTVerifier initValidator() {
        final Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());
        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
    }
}
