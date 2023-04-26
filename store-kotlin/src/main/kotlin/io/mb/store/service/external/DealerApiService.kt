package io.mb.store.service.external

import io.mb.store.resource.dto.DealerDto
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class DealerApiService(
    private val dealerApiWebClient: WebClient
) {
    private companion object {
        private const val DEALER_BY_ID_PATH = "/dealers/{dealerId}"
    }

    suspend fun getDealer(dealerId: String): DealerDto {
        return dealerApiWebClient.get()
            .uri {
                it.path(DEALER_BY_ID_PATH).build(dealerId)
            }
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .awaitBody<DealerDto>()
    }
}