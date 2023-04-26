package io.mb.store.resource.dto

// The vehicle api has a specification that follows
// openapi standard and in a real world we would have
// these DTOs generated from the API

data class VehicleDto(
    val vin: String,
    val brand: String,
    val model: String,
    val fuelType: FuelType,
    val mileageUnit: MileageUnit,
    val kw: Int?,
    val mileage: Int?,
)