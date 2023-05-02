package io.mb.store.service

import io.mb.store.resource.dto.DealerVehiclesDto
import io.mb.store.resource.dto.VehicleDto
import io.mb.store.service.external.DealerApiService
import io.mb.store.service.external.VehicleApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service

@Service
class VehicleService(
    private val storeService: StoreService,
    private val vehicleApiService: VehicleApiService,
    private val dealerApiService: DealerApiService,
) {

    fun getVehiclesFromDealer(dealerId: String): Flow<VehicleDto> {
        return storeService.findByDealerId(dealerId).asFlow()
            .map { vehicleApiService.getVehicle(it.vin) }
    }

    /**
     * TODO Implement the method below
     *
     * This method should return all vehicles
     * that are available in a dealer
     */
    fun getAllVehicles(): Flow<DealerVehiclesDto> =
        TODO("Implement this method")
}