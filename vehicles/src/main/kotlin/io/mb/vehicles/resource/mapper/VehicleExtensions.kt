package io.mb.vehicles.resource.mapper

import io.mb.vehicles.model.Vehicle
import io.mb.vehicles.resource.dto.FuelType
import io.mb.vehicles.resource.dto.MileageUnit
import io.mb.vehicles.resource.dto.VehicleDto

fun Vehicle.toVehicleDto(): VehicleDto =
    VehicleDto(
        id = checkNotNull(id),
        vin = vin,
        brand = brand,
        model = model,
        fuelType = FuelType.valueOf(fuelType),
        kw = kw,
        mileage = mileage,
        mileageUnit = MileageUnit.valueOf(mileageUnit)
    )
