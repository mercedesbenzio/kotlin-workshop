package io.mb.store.service

import io.mb.store.resource.dto.DealerVehiclesDto
import io.mb.store.resource.dto.ItemDto
import io.mb.store.resource.dto.VehicleDto
import io.mb.store.service.external.DealerApiService
import io.mb.store.service.external.VehicleApiService
import kotlinx.coroutines.flow.*
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
     * TODO (DONE) Implement the method below
     *
     * This method should return all vehicles
     * that are available in a dealer
     */
    fun getAllVehicles(): Flow<DealerVehiclesDto> = flow {
        storeService.findAllGroupedByDealerId().forEach { (dealerId, vehicles) ->
            val dealerDto = dealerApiService.getDealer(dealerId)

            val vehicleDtos = vehicles.asSequence().map(ItemDto::vin).let {
                vehicleApiService.getVehicles(it)
            }.toList()

            emit(DealerVehiclesDto(dealerDto.name, vehicleDtos))
        }
    }
}