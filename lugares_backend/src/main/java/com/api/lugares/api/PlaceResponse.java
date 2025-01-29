package com.api.lugares.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Resposta padrão do sistema.")
public record PlaceResponse(
        @Schema(description = "Nome do lugar", example = "My Place")
        String name,

        @Schema(description = "Slug do lugar", example = "my-place")
        String slug,

        @Schema(description = "Estado do lugar", example = "São Paulo")
        String state,

        @Schema(description = "Data de criação do lugar", example = "2025-01-22T11:03:27.81868")
        LocalDateTime createdAt,

        @Schema(description = "Data da última alteração do lugar", example = "2025-01-22T11:03:27.81868")
        LocalDateTime updatedAt
) {
}
