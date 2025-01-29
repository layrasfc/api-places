package com.api.lugares.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição padrão no sistema.")
public record PlaceRequest(
        @Schema(description = "Nome lugar", example = "My Place")
        String name,

        @Schema(description = "Estado do lugar", example = "São Paulo")
        String state
) {
}
