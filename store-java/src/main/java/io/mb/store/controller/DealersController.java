package io.mb.store.controller;

import io.mb.store.resource.dto.DealerVehiclesDto;
import io.mb.store.resource.dto.VehicleDto;
import io.mb.store.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static io.mb.store.resource.Constants.*;

@RestController
@RequestMapping(DEALERS_MAPPING)
@RequiredArgsConstructor
@Slf4j
public class DealersController {
    private final VehicleService vehicleService;

    @GetMapping
    public Flux<DealerVehiclesDto> getAllVehiclesDetails() {
        log.info("Fetching all vehicles from all dealers");
        Flux<DealerVehiclesDto> vehicles = vehicleService.getAllVehicles();
        log.info("Returned all vehicles available");
        return vehicles;
    }

    @GetMapping(DEALERS_VEHICLES_MAPPING)
    public Flux<VehicleDto> getAllVehiclesFromDealer(
            @PathVariable(DEALER_PARAM) final String dealerId
    ) {
        log.info("Fetching all vehicles available in dealer " + dealerId);
        Flux<VehicleDto> vehicles = vehicleService.getVehiclesFromDealer(dealerId);
        log.info("Returned all vehicles available on dealer " + dealerId);
        return vehicles;
    }
}
