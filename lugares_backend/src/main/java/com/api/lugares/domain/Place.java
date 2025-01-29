package com.api.lugares.domain;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public record Place(
        @Id Long id,
        @NotBlank String name,
        @NotBlank String slug,
        @NotBlank String state,

        @CreatedDate LocalDateTime createdAt,
        @LastModifiedDate LocalDateTime updatedAt
        ) {
}
