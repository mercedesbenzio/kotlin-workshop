package io.mb.store.controller

import io.mb.store.resource.DEALERS_MAPPING
import io.mb.store.resource.DEALER_PARAM
import io.mb.store.resource.DEALER_VEHICLES_MAPPING
import io.mb.store.resource.dto.DealerVehiclesDto
import io.mb.store.resource.dto.VehicleDto
import io.mb.store.service.VehicleService
import kotlinx.coroutines.flow.Flow
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(DEALERS_MAPPING)
class DealersController(
    private val vehicleService: VehicleService,
) {

    private companion object {
        private val logger = KotlinLogging.logger { }
    }

    @GetMapping
    fun getAllVehiclesDetails(): Flow<DealerVehiclesDto> {
        logger.info { "Fetching all vehicles from all dealers" }
        return vehicleService.getAllVehicles().also {
            logger.info { "Returned all vehicles available" }
        }
    }

    @GetMapping(DEALER_VEHICLES_MAPPING)
    fun getAllVehiclesFromDealer(
        @PathVariable(DEALER_PARAM) dealerId: String
    ): Flow<VehicleDto> {
        logger.info { "Fetching all vehicles available in dealer $dealerId" }
        return vehicleService.getVehiclesFromDealer(dealerId).also {
            logger.info { "Returned all vehicles available on dealer $dealerId" }
        }
    }
}