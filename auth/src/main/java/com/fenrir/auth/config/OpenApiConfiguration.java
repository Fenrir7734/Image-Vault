package com.fenrir.auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Auth Service Rest API",
                version = "0.1.0",
                description = "",
                contact = @Contact(
                        name = "F3NRIR",
                        email = "hitex1999@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        security = {
                @SecurityRequirement(
                        name = "google",
                        scopes = "profile, email"
                ),
        }
)
@SecurityScheme(
        name = "google",
        type = SecuritySchemeType.OAUTH2,
        in = SecuritySchemeIn.COOKIE,
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow(
                        scopes = {
                                @OAuthScope(name = "profile"),
                                @OAuthScope(name = "email")
                        },
                        authorizationUrl = "http://localhost:8080/oauth2/authorization/google"
                )
        )
)
class OpenApiConfiguration {
}
