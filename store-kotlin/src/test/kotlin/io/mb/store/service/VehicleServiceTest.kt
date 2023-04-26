package io.mb.store.service

import io.kotest.assertions.fail
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equals.shouldBeEqual
import io.mb.store.resource.dto.ItemDto
import io.mb.store.resource.dto.VehicleDto
import io.mb.store.service.external.DealerApiService
import io.mb.store.service.external.VehicleApiService
import io.mockk.*
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

        coVerify {
            vehicleIdSlot.forEach {
                vehicleApiService.getVehicle(it)
            }
        }

        confirmVerified(storeService, vehicleApiService, dealerApiService)
    }

    /**
     * TODO implement test method below
     *
     * This method should test that [VehicleService.getAllVehicles]
     * returns the information of all available vehicles in all dealers
     * grouped by dealer id.
     */
    xtest("getAllVehicles has expected results") {
        fail("Implement the missing method!")
    }

    afterEach {
        clearAllMocks()
    }
})