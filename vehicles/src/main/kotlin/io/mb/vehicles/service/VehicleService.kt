package io.mb.vehicles.service

import io.mb.vehicles.model.Vehicle
import io.mb.vehicles.repository.VehicleRepository
import io.mb.vehicles.resource.dto.FuelType
import io.mb.vehicles.resource.dto.VehicleCreateDto
import io.mb.vehicles.resource.dto.VehicleDto
import io.mb.vehicles.resource.exception.VehicleNotFoundException
import io.mb.vehicles.resource.mapper.toVehicle
import io.mb.vehicles.resource.mapper.toVehicleDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VehicleService(
    private val vehicleRepository: VehicleRepository,
) {

    fun findAll(fuelType: FuelType?): List<VehicleDto> {
        return vehicleRepository.run {
            if (fuelType == null) findAll()
            else findByFuelType(fuelType.name)
        }.map(Vehicle::toVehicleDto)
    }

    fun findByVin(vin: String): VehicleDto {
        return vehicleRepository.findByVin(vin)
            ?.toVehicleDto()
            ?: throw VehicleNotFoundException(vin)
    }

    @Transactional
    fun save(vehicleCreateDto: VehicleCreateDto): VehicleDto {
        return vehicleRepository.save(vehicleCreateDto.toVehicle()).toVehicleDto()
    }
}