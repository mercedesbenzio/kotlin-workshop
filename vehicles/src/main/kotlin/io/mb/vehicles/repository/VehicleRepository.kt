package io.mb.vehicles.repository

import io.mb.vehicles.model.Vehicle
import org.springframework.data.jpa.repository.JpaRepository

interface VehicleRepository : JpaRepository<Vehicle, String> {
    fun findByVin(vin: String): Vehicle?
}