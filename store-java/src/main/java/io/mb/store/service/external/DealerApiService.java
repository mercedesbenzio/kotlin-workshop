package io.mb.store.service.external;

import io.mb.store.resource.dto.DealerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DealerApiService {
    private static final String DEALER_BY_ID_PATH = "/dealers/{dealerId}";

    private final WebClient dealerApiWebClient;

    public Mono<DealerDto> getDealer(final String dealerId) {
        return dealerApiWebClient.get()
                .uri(it -> it.path(DEALER_BY_ID_PATH).build(dealerId))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(DealerDto.class);
    }
}
