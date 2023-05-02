package io.mb.vehicles.controller

import io.mb.vehicles.resource.VEHICLES_BASE_PATH
import io.mb.vehicles.resource.VEHICLES_BY_VIN
import io.mb.vehicles.resource.VIN_PARAM
import io.mb.vehicles.resource.dto.VehicleCreateDto
import io.mb.vehicles.resource.dto.VehicleDto
import io.mb.vehicles.service.VehicleService
import jakarta.validation.Valid
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(VEHICLES_BASE_PATH)
class VehicleController(
    private val vehicleService: VehicleService
) {

    private companion object {
        private val logger = KotlinLogging.logger { }
    }

    @GetMapping(VEHICLES_BY_VIN)
    fun findByVin(@PathVariable(VIN_PARAM) vin: String): VehicleDto {
        logger.info { "Fetching vehicle with vin: $vin" }
        return vehicleService.findByVin(vin).also {
            logger.info { "Found vehicle: $it" }
        }
    }

    @GetMapping
    fun findAll(): List<VehicleDto> {
        logger.info { "Fetching all vehicles" }
        return vehicleService.findAll().also {
            logger.info { "Returned all vehicles" }
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody vehicleDto: VehicleCreateDto): VehicleDto {
        logger.info { "Creating vehicle with vin ${vehicleDto.vin}" }
        return vehicleService.save(vehicleDto).also {
            logger.info { "Vehicle with vin ${it.vin} created" }
        }
    }

    /**
     * TODO Implement the method below
     *
     * Implement a find by fuel method that retrieves all vehicles
     * with the given fuel type.
     *
     * Don't forget the mapping for the HTTP method and the response status ;)
     *
     * Also, you will need to handover to the method the fuel type you are searching for...
     */
    fun findByFuelType(
        /** Add missing arguments **/
    ): List<VehicleDto> {
        TODO("Implement method body")
    }
}