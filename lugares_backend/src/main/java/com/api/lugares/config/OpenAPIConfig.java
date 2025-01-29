package com.api.lugares.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title="API de Lugares",
                version = "1.0.0",
                description = "API para gerenciar e filtrar os lugares cadastrados no sistema."
        )
)
public class OpenAPIConfig {
}
