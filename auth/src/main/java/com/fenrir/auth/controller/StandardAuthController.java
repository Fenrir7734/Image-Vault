package com.fenrir.auth.controller;

import com.fenrir.auth.dto.request.ExchangeRequest;
import com.fenrir.auth.dto.request.RegisterRequest;
import com.fenrir.auth.service.StandardAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fenrir.auth.security.SecurityConstants.ACCESS_CONTROL_EXPOSE_HEADERS;
import static com.fenrir.auth.security.SecurityConstants.BEARER;
import static com.fenrir.auth.security.SecurityConstants.X_TOKEN;

@RestController
@RequestMapping("/api/standard")
@RequiredArgsConstructor
public class StandardAuthController {
    private final StandardAuthService standardAuthService;

    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest request) {
        standardAuthService.registerUser(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/exchange")
    ResponseEntity<Void> generateToken(@RequestBody @Valid ExchangeRequest request) {
        String token = standardAuthService.exchangeCredentials(request);
        return ResponseEntity.ok()
                .header(X_TOKEN, BEARER + token)
                .header(ACCESS_CONTROL_EXPOSE_HEADERS, X_TOKEN)
                .build();
    }
}
