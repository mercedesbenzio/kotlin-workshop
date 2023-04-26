package io.mb.store.resource.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(requiredProperties = {"id", "vin", "dealerId"})
public record ItemDto(long id, String vin, String dealerId) {
}
