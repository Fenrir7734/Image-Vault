package com.fenrir.auth.controller;

import com.fenrir.auth.dto.request.RegisterRequest;
import com.fenrir.auth.service.StandardAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class StandardAuthController {
    private final StandardAuthService standardAuthService;

    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest request) {
        standardAuthService.registerUser(request);
        return ResponseEntity.ok().build();
    }
}
