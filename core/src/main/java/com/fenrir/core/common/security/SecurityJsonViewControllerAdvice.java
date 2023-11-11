package com.fenrir.core.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class SecurityJsonViewControllerAdvice extends AbstractMappingJacksonResponseBodyAdvice {
    private final AuthenticationFacade authenticationFacade;

    @Override
    protected void beforeBodyWriteInternal(
            MappingJacksonValue bodyContainer,
            MediaType contentType,
            MethodParameter returnType,
            ServerHttpRequest request,
            ServerHttpResponse response) {

        if (!authenticationFacade.isAuthorized()) {
            return;
        }

        Class jsonView = authenticationFacade.getRoles()
                .stream()
                .map(RoleView.MAPPING::get)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        bodyContainer.setSerializationView(jsonView);
    }
}
