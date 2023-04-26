package io.mb.store.service.external;

import io.mb.store.resource.dto.VehicleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class VehicleApiService {
    private static final String VEHICLE_BY_VIN_PATH = "/vehicles/{vin}";
    private static final int REQUEST_CONCURRENCY_LEVEL = 10;

    private final WebClient vehicleApiWebClient;

    private Flux<VehicleDto> getVehiclesConcurrently(final List<String> vehicles) {
        Stream<Mono<VehicleDto>> stream = vehicles.stream().map(this::getVehicle);
        Iterable<Mono<VehicleDto>> iterable = stream::iterator;
        return Flux.merge(iterable);
    }

    public Flux<VehicleDto> getVehicles(final Stream<String> vins) {
        return Flux.fromStream(vins).buffer(REQUEST_CONCURRENCY_LEVEL)
                .flatMap(this::getVehiclesConcurrently);
    }

    public Mono<VehicleDto> getVehicle(final String vin) {
        return vehicleApiWebClient.get()
                .uri(it -> it.path(VEHICLE_BY_VIN_PATH).build(vin))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(VehicleDto.class);
    }
}
