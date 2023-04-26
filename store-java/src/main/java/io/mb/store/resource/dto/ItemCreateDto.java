package io.mb.store.resource.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(requiredProperties = {"vin", "dealerId"})
public record ItemCreateDto(@NotBlank String vin, @NotBlank String dealerId) {
}
