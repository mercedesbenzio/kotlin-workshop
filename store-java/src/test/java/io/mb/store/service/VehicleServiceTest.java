package io.mb.store.service;

import io.mb.store.resource.dto.*;
import io.mb.store.service.external.DealerApiService;
import io.mb.store.service.external.VehicleApiService;
import org.assertj.core.api.Assertions;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
     * TODO (DONE) Implement test method below
     * <br>
     * This method should test that {@link VehicleService#getAllVehicles()}
     * returns the information of all available vehicles in all dealers
     * grouped by dealer id.
     */
    @Test
    void getAllVehicles_has_expected_results() {
        String brand = "Mercedes-Benz";
        String dealerId1 = "dealer1", dealerId2 = "dealer2";
        String vin1 = "vin1", vin2 = "vin2", vin3 = "vin3";

        ItemDto item1 = new ItemDto(1L, vin1, dealerId1);
        ItemDto item2 = new ItemDto(2L, vin3, dealerId1);
        ItemDto item3 = new ItemDto(3L, vin2, dealerId2);

        List<ItemDto> dealer1Items = List.of(item1, item2);
        List<ItemDto> dealer2Items = List.of(item3);

        Map<String, List<ItemDto>> groupedByDealerItems = new LinkedHashMap<>();
        groupedByDealerItems.put(dealerId1, dealer1Items);
        groupedByDealerItems.put(dealerId2, dealer2Items);

        DealerDto dealer1 = new DealerDto(dealerId1, "ABC Auto Sales", null);
        DealerDto dealer2 = new DealerDto(dealerId2, "XYZ Motors", null);

        VehicleDto vehicle1 = new VehicleDto(
                vin1, brand, "C220d",
                FuelType.DIESEL, MileageUnit.KM, null, null
        );
        VehicleDto vehicle2 = new VehicleDto(
                vin2, brand, "A180",
                FuelType.GASOLINE, MileageUnit.KM, null, null
        );
        VehicleDto vehicle3 = new VehicleDto(
                vin3, brand, "EQA250",
                FuelType.ELECTRIC, MileageUnit.KM, null, null
        );

        List<VehicleDto> dealer1Vehicles = List.of(vehicle1, vehicle3);
        List<VehicleDto> dealer2Vehicles = List.of(vehicle2);

        Mockito.when(storeService.finAllGroupedByDealerId())
                .thenReturn(groupedByDealerItems);

        Mockito.when(dealerApiService.getDealer(Mockito.anyString()))
                .thenReturn(
                        Mono.justOrEmpty(dealer1),
                        Mono.justOrEmpty(dealer2)
                );

        Mockito.when(vehicleApiService.getVehicles(Mockito.any()))
                .thenReturn(
                        Flux.fromIterable(dealer1Vehicles),
                        Flux.fromIterable(dealer2Vehicles)
                );

        Flux<DealerVehiclesDto> allVehicles = vehicleService.getAllVehicles();

        StepVerifier.create(allVehicles)
                .expectNext(new DealerVehiclesDto(dealer1.name(), dealer1Vehicles))
                .expectNext(new DealerVehiclesDto(dealer2.name(), dealer2Vehicles))
                .verifyComplete();

        ArgumentCaptor<String> dealerIdCaptor = ArgumentCaptor.forClass(String.class);
        @SuppressWarnings("unchecked") ArgumentCaptor<Stream<String>> streamArgumentCaptor =
                ArgumentCaptor.forClass(Stream.class);

        Mockito.verify(storeService).finAllGroupedByDealerId();
        Mockito.verify(dealerApiService, Mockito.times(2))
                .getDealer(dealerIdCaptor.capture());
        Mockito.verify(vehicleApiService, Mockito.times(2))
                .getVehicles(streamArgumentCaptor.capture());

        Assertions.assertThat(dealerIdCaptor.getAllValues()).containsExactly(
                dealerId1, dealerId2
        );

        Assertions.assertThat(streamArgumentCaptor.getAllValues())
                .hasSize(2)
                .satisfies(
                        x -> Assertions.assertThat(x.toList())
                                .containsExactly(vin1, vin3),
                        Assertions.atIndex(0)
                )
                .satisfies(
                        x -> Assertions.assertThat(x.toList())
                                .containsExactly(vin2),
                        Assertions.atIndex(1)
                );
    }
}