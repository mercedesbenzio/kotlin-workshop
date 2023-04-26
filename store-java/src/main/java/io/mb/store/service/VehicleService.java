package io.mb.store.service;

import io.mb.store.resource.dto.DealerVehiclesDto;
import io.mb.store.resource.dto.ItemDto;
import io.mb.store.resource.dto.VehicleDto;
import io.mb.store.service.external.DealerApiService;
import io.mb.store.service.external.VehicleApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final StoreService storeService;
    private final VehicleApiService vehicleApiService;
    private final DealerApiService dealerApiService;

    public Flux<VehicleDto> getVehiclesFromDealer(final String dealerId) {
        return Flux.fromIterable(storeService.findByDealerId(dealerId))
                .flatMap(itemDto -> vehicleApiService.getVehicle(itemDto.vin()));
    }

    public Flux<DealerVehiclesDto> getAllVehicles() {
        var entries = storeService.finAllGroupedByDealerId().entrySet();
        return Flux.fromIterable(entries).flatMap(entry ->
                dealerApiService.getDealer(entry.getKey()).flatMap(dealerDto -> {
                    Stream<String> vins = entry.getValue().stream().map(ItemDto::vin);
                    return vehicleApiService.getVehicles(vins).collectList()
                            .map(x -> new DealerVehiclesDto(dealerDto.name(), x));
                })
        );
    }
}
