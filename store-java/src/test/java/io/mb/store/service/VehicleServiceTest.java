package io.mb.store.service;

import io.mb.store.resource.dto.ItemDto;
import io.mb.store.resource.dto.VehicleDto;
import io.mb.store.service.external.DealerApiService;
import io.mb.store.service.external.VehicleApiService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class VehicleServiceTest {

    @Mock
    private StoreService storeService;

    @Mock
    private VehicleApiService vehicleApiService;

    @Mock
    private DealerApiService dealerApiService;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void getVehiclesFromDealer_has_expected_results() {
        String dealerId = "abcd";
        String vehicleId1 = "vin1";
        String vehicleId2 = "vin2";

        ItemDto item1 = new ItemDto(1, "vin1", dealerId);
        ItemDto item2 = new ItemDto(1, "vin2", dealerId);

        VehicleDto vehicle1 = Mockito.mock(VehicleDto.class);
        VehicleDto vehicle2 = Mockito.mock(VehicleDto.class);

        Mockito.when(storeService.findByDealerId(Mockito.anyString()))
                .thenReturn(List.of(item1, item2));

        Mockito.when(vehicleApiService.getVehicle(Mockito.anyString()))
                .thenReturn(Mono.just(vehicle1), Mono.just(vehicle2));

        Flux<VehicleDto> vehiclesFromDealer = vehicleService.getVehiclesFromDealer(dealerId);

        StepVerifier.create(vehiclesFromDealer)
                .expectNext(vehicle1, vehicle2)
                .verifyComplete();

        var dealerIdCaptor = ArgumentCaptor.forClass(String.class);
        var vinCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(storeService).findByDealerId(dealerIdCaptor.capture());
        Mockito.verify(vehicleApiService, times(2)).getVehicle(vinCaptor.capture());

        Assertions.assertThat(dealerIdCaptor.getValue()).isEqualTo(dealerId);
        Assertions.assertThat(vinCaptor.getAllValues())
                .hasSize(2)
                .containsExactly(vehicleId1, vehicleId2);
    }

    /**
     * TODO Implement test method below
     * <br>
     * This method should test that {@link VehicleService#getAllVehicles()}
     * returns the information of all available vehicles in all dealers
     * grouped by dealer id.
      */
    @Test
    @Disabled
    void getAllVehicles_has_expected_results() {
        Assertions.fail("Implement the missing method!");
    }
}