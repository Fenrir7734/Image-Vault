package com.fenrir.auth.controller;

import com.fenrir.auth.security.AuthenticationFacade;
import com.fenrir.auth.security.jwt.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fenrir.auth.security.SecurityConstants.ACCESS_CONTROL_EXPOSE_HEADERS;
import static com.fenrir.auth.security.SecurityConstants.BEARER;
import static com.fenrir.auth.security.SecurityConstants.JSESSIONID;
import static com.fenrir.auth.security.SecurityConstants.X_TOKEN;

@RestController
@RequestMapping("/api/oauth")
@RequiredArgsConstructor
class SocialAuthController {


    private final AuthenticationFacade authenticationFacade;
    private final JwtUtils jwtUtils;

    @GetMapping("/exchange")
    ResponseEntity<Void> exchange(HttpServletRequest request, HttpServletResponse response) {
        authenticationFacade.assertAuthenticated();

        String token = jwtUtils.generateAccessToken(authenticationFacade.getUserPrincipal());
        invalidateSession(request);
        unsetJSessionIdCookie(response);

        return ResponseEntity.ok()
                .header(X_TOKEN, BEARER + token)
                .header(ACCESS_CONTROL_EXPOSE_HEADERS, X_TOKEN)
                .build();
    }

    private void invalidateSession(HttpServletRequest request) {
        SecurityContextHolder.createEmptyContext();
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
    }

    private void unsetJSessionIdCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(JSESSIONID, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
