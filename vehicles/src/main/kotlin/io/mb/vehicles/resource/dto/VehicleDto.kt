package io.mb.vehicles.resource.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class VehicleDto(
    @field:NotNull
    val id: Long,
    @field:NotBlank
    val vin: String,
    @field:NotBlank
    val brand: String,
    @field:NotBlank
    val model: String,
    @field:NotNull
    val fuelType: FuelType,
    val kw: Int?,
    val mileage: Int?,
    @field:NotNull
    val mileageUnit: MileageUnit
)

