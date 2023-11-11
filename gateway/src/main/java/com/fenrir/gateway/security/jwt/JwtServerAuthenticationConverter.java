package com.fenrir.gateway.security.jwt;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtServerAuthenticationConverter implements ServerAuthenticationConverter {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String EMPTY = "";

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange)
                .flatMap(ex -> Mono.justOrEmpty(extractToken(ex.getRequest())))
                .map(token -> token == null ? EMPTY : token)
                .map(this::removeBearerPartFromToken)
                .map(token -> new UsernamePasswordAuthenticationToken(token, token, null));
    }

    private String extractToken(ServerHttpRequest request) {
        return request.getHeaders().getFirst(AUTHORIZATION);
    }

    private String removeBearerPartFromToken(String token) {
        return token.startsWith(BEARER) ? token.substring(BEARER.length()) : token;
    }
}
