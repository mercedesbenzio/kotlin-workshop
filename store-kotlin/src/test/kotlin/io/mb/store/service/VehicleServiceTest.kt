package io.mb.store.service

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldMatchEach
import io.kotest.matchers.equals.shouldBeEqual
import io.mb.store.resource.dto.*
import io.mb.store.service.external.DealerApiService
import io.mb.store.service.external.VehicleApiService
import io.mockk.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList

class VehicleServiceTest : FunSpec({

    val storeService: StoreService = mockk()
    val vehicleApiService: VehicleApiService = mockk()
    val dealerApiService: DealerApiService = mockk()
    val vehicleService = VehicleService(storeService, vehicleApiService, dealerApiService)

    test("getVehiclesFromDealer has expected results") {
        val dealerId = "abcd"
        val vehicleId1 = "vin1"
        val vehicleId2 = "vin2"

        val item1 = ItemDto(id = 1, vin = vehicleId1, dealerId = dealerId)
        val item2 = ItemDto(id = 2, vin = vehicleId2, dealerId = dealerId)

        val vehicle1 = mockk<VehicleDto>()
        val vehicle2 = mockk<VehicleDto>()

        val dealerIdSlot = slot<String>()
        val vehicleIdSlot = mutableListOf<String>()

        every {
            storeService.findByDealerId(capture(dealerIdSlot))
        } returns listOf(item1, item2)

        coEvery {
            vehicleApiService.getVehicle(capture(vehicleIdSlot))
        } returns vehicle1 andThen vehicle2

        val vehiclesFromDealer = vehicleService.getVehiclesFromDealer(dealerId)

        vehiclesFromDealer.toList() shouldContainExactly listOf(vehicle1, vehicle2)

        dealerIdSlot.isCaptured shouldBeEqual true
        dealerIdSlot.captured shouldBeEqual dealerId
        vehicleIdSlot.shouldHaveSize(2)
            .shouldContainExactly(vehicleId1, vehicleId2)

        verify {
            storeService.findByDealerId(dealerIdSlot.captured)
        }

        vehicleIdSlot.forEach {
            coVerify {
                vehicleApiService.getVehicle(it)
            }
        }

        confirmVerified(storeService, vehicleApiService, dealerApiService)
    }

    /**
     * TODO (DONE) implement test method below
     *
     * This method should test that [VehicleService.getAllVehicles]
     * returns the information of all available vehicles in all dealers
     * grouped by dealer id.
     */
    test("getAllVehicles has expected results") {
        val brand = "Mercedes-Benz"
        val dealerId1 = "dealer1"
        val dealerId2 = "dealer2"
        val vin1 = "vin1"
        val vin2 = "vin2"
        val vin3 = "vin3"

        val item1 = ItemDto(1L, vin1, dealerId1)
        val item2 = ItemDto(2L, vin3, dealerId1)
        val item3 = ItemDto(3L, vin2, dealerId2)

        val dealer1Items = listOf(item1, item2)
        val dealer2Items = listOf(item3)

        val groupedByDealerItems: Map<String, List<ItemDto>> = mapOf(
            dealerId1 to dealer1Items,
            dealerId2 to dealer2Items
        )

        val dealer1 = DealerDto(dealerId1, "ABC Auto Sales", null)
        val dealer2 = DealerDto(dealerId2, "XYZ Motors", null)

        val vehicle1 = VehicleDto(
            vin1, brand, "C220d",
            FuelType.DIESEL, MileageUnit.KM, null, null
        )
        val vehicle2 = VehicleDto(
            vin2, brand, "A180",
            FuelType.GASOLINE, MileageUnit.KM, null, null
        )
        val vehicle3 = VehicleDto(
            vin3, brand, "EQA250",
            FuelType.ELECTRIC, MileageUnit.KM, null, null
        )

        val dealer1Vehicles = listOf(vehicle1, vehicle3)
        val dealer2Vehicles = listOf(vehicle2)

        val dealerSlot = mutableListOf<String>()
        val vinsSlot = mutableListOf<Sequence<String>>()

        every {
            storeService.findAllGroupedByDealerId()
        } returns groupedByDealerItems

        coEvery {
            dealerApiService.getDealer(capture(dealerSlot))
        } returns dealer1 andThen dealer2

        every {
            vehicleApiService.getVehicles(capture(vinsSlot))
        } returns dealer1Vehicles.asFlow() andThen dealer2Vehicles.asFlow()

        val allVehicles: Flow<DealerVehiclesDto> = vehicleService.getAllVehicles()

        allVehicles.toList().shouldContainExactly(
            DealerVehiclesDto(dealer1.name, dealer1Vehicles),
            DealerVehiclesDto(dealer2.name, dealer2Vehicles)
        )

        dealerSlot.shouldHaveSize(2)
            .shouldContainExactly(dealerId1, dealerId2)

        vinsSlot.shouldHaveSize(2)
            .shouldMatchEach(
                { it.toList().shouldContainExactly(vin1, vin3) },
                { it.toList().shouldContainExactly(vin2) },
            )

        verify(exactly = 1) {
            storeService.findAllGroupedByDealerId()
        }

        dealerSlot.forEach {
            coVerify(exactly = 1) {
                dealerApiService.getDealer(it)
            }
        }

        vinsSlot.forEach {
            coVerify(exactly = 1) {
                vehicleApiService.getVehicles(refEq(it))
            }
        }

        confirmVerified(storeService, vehicleApiService, dealerApiService)
    }

    afterEach {
        clearAllMocks()
    }
})