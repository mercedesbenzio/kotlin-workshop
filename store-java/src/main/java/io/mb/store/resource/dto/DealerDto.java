package io.mb.store.resource.dto;

// The dealer api has a specification that follows
// openapi standard and in a real world we would have
// these DTOs generated from the API

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(requiredProperties = {"id", "name"})
public record DealerDto(String id, String name, String description) {
}
