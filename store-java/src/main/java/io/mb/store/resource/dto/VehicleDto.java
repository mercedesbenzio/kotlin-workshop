package io.mb.store.resource.dto;

// The vehicle api has a specification that follows
// openapi standard and in a real world we would have
// these DTOs generated from the API

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(requiredProperties = {"vin", "brand", "model", "fuelType", "mileageUnit"})
public record VehicleDto(
        String vin,
        String brand,
        String model,
        FuelType fuelType,
        MileageUnit mileageUnit,
        Integer kw,
        Integer mileage
) {
}
