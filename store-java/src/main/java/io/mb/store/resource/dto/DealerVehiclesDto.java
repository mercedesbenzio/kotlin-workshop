package io.mb.store.resource.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(requiredProperties = {"dealer", "vehicles"})
public record DealerVehiclesDto(String dealer, List<VehicleDto> vehicles) {
}
