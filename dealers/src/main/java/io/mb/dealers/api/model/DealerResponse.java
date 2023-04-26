package io.mb.dealers.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(requiredProperties = {"id", "name"})
public record DealerResponse(String id, String name, String description) {
}
