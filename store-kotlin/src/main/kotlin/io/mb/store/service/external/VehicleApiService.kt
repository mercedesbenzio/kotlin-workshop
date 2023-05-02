package io.mb.store.service.external

import io.mb.store.resource.dto.VehicleDto
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class VehicleApiService(
    private val vehicleApiWebClient: WebClient
) {
    private companion object {
        private const val VEHICLE_BY_VIN_PATH = "/vehicles/{vin}"
        private const val REQUEST_CONCURRENCY_LEVEL = 10
    }

    private suspend fun getVehiclesConcurrently(vehicles: List<String>): List<VehicleDto> = coroutineScope {
        vehicles.map { async { getVehicle(it) } }.awaitAll()
    }

    @OptIn(FlowPreview::class)
    fun getVehicles(vins: Sequence<String>): Flow<VehicleDto> =
        vins.chunked(REQUEST_CONCURRENCY_LEVEL).asFlow()
            .flatMapConcat { getVehiclesConcurrently(it).asFlow() }

    suspend fun getVehicle(vin: String): VehicleDto =
        vehicleApiWebClient.get()
            .uri { it.path(VEHICLE_BY_VIN_PATH).build(vin) }
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .awaitBody<VehicleDto>()
}