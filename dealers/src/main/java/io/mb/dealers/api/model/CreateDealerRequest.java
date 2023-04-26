package io.mb.dealers.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(requiredProperties = {"id", "name"})
public record CreateDealerRequest(@NotBlank String id, @NotBlank String name, String description) {
}
