package com.yassine.practice.OpenApiDoc;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Yassine El Azami El Idrissi",
                        url = "https://github.com/YassineAzamiIdrissi",
                        email = "yassineazami9@gmail.com"
                ),
                description = "this is an OpenApi Spec for our backend",
                title = "bikes app specification",
                version = "1.0",
                license = @License(
                        url = "license...",
                        name = "license"
                ),
                termsOfService = "some terms of service..."
        ),
        servers = {
                @Server(
                        url = "http://localhost:8088/api/v1",
                        description = "local environment"
                ),
                @Server(
                        url = "some_url",
                        description = "production environment"
                )
        },
        security = @SecurityRequirement(
                name = "bearerAuth"
        )
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Jwt approach to secure our backend api",
        in = SecuritySchemeIn.HEADER,
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig
{}
