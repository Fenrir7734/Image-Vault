package com.fenrir.gateway.filter;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.UUID;

@Slf4j
@Component
public class LoggingPrePostFilter implements GlobalFilter {
    private static final String EMPTY = "";
    private static final String MASK = "*****";
    private static final String BEARER = "Bearer ";
    private static final String MASKED_BEARER = BEARER + MASK;
    private static final String NON_APPLICABLE = "N/A";
    private static final Collection<String> SENSITIVE_HEADERS = Lists.newArrayList(
            "Authorization",
            "X-Token",
            "Cookie",
            "Set-Cookie",
            "WWW-Authenticate",
            "Proxy-Authorization"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long startTime = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();
        logRequest(exchange.getRequest(), uuid);
        return chain.filter(exchange).then(Mono.fromRunnable(() ->
                logResponse(exchange.getResponse(), uuid, startTime))
        );
    }

    private void logRequest(ServerHttpRequest request, String uuid) {
        String ipAddress = extractClientIP(request);
        String method = request.getMethod().toString();
        String path = request.getPath().toString();
        String headers = maskSensitiveHeaders(request.getHeaders()).toSingleValueMap().toString();
        log.info("[{}]\t[REQUEST]\t{} {} {} {}", uuid, ipAddress, method, path, headers);
    }

    private void logResponse(ServerHttpResponse response, String uuid, long startTime) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        String headers = maskSensitiveHeaders(response.getHeaders()).toSingleValueMap().toString();
        int status = getHttpStatusCode(response);
        log.info("[{}]\t[RESPONSE]\t{} {} ({} ms)", uuid, status, headers, elapsedTime);
    }

    private int getHttpStatusCode(ServerHttpResponse response) {
        final HttpStatusCode statusCode = response.getStatusCode();
        return statusCode != null ? statusCode.value() : HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    private String extractClientIP(ServerHttpRequest request) {
        final InetSocketAddress clientAddress = request.getRemoteAddress();
        return clientAddress != null ? clientAddress.getAddress().getHostAddress() : NON_APPLICABLE;
    }

    private HttpHeaders maskSensitiveHeaders(HttpHeaders headers) {
        final HttpHeaders maskedHeaders = new HttpHeaders();
        headers.forEach((headerName, headerValues) -> {
            if (SENSITIVE_HEADERS.contains(headerName)) {
                headerValues = headerValues.stream()
                        .map(value -> maskHeaderValue(headerName, value))
                        .toList();
            }
            maskedHeaders.put(headerName, headerValues);
        });
        return maskedHeaders;
    }

    private String maskHeaderValue(String headerName, String value) {
        if (value == null) return EMPTY;
        if (value.startsWith(BEARER)) return MASKED_BEARER;

        if (headerName.equals("Set-Cookie") || headerName.equals("Cookie")) {
            return maskCookieValue(value);
        }

        return MASK;
    }

    private String maskCookieValue(String value) {
        final String[] cookies = value.split(";");
        final Collection<String> maskedCookiesParts = Lists.newArrayList();

        for (String cookie: cookies) {
            if (cookie.contains("=")) {
                final String[] parts = cookie.split("=");
                final String maskedCookiePart = String.format("%s=%s", parts[0], MASK);
                maskedCookiesParts.add(maskedCookiePart);
            }
        }

        return String.join("; ", maskedCookiesParts);
    }
}
