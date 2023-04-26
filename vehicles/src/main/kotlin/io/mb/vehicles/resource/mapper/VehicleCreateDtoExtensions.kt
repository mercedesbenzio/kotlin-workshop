package io.mb.vehicles.resource.mapper

import io.mb.vehicles.model.Vehicle
import io.mb.vehicles.resource.dto.VehicleCreateDto

fun VehicleCreateDto.toVehicle(): Vehicle =
    Vehicle(
        vin = vin,
        brand = brand,
        model = model,
        fuelType = fuelType.name,
        kw = kw,
        mileage = mileage,
        mileageUnit = mileageUnit.name
    )