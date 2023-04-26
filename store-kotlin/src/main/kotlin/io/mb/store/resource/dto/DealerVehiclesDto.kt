package io.mb.store.resource.dto

data class DealerVehiclesDto(
    val dealer: String,
    val vehicles: List<VehicleDto> = emptyList()
)