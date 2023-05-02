package io.mb.vehicles.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import io.mb.vehicles.model.Vehicle
import io.mb.vehicles.repository.VehicleRepository
import io.mb.vehicles.resource.dto.FuelType
import io.mb.vehicles.resource.dto.MileageUnit
import io.mb.vehicles.resource.exception.VehicleNotFoundException
import io.mb.vehicles.resource.mapper.toVehicleDto
import io.mockk.*

class VehicleServiceTest : FunSpec({

    val vehicleRepository = mockk<VehicleRepository>()
    val vehicleService = VehicleService(vehicleRepository)

    test("findByVin throws exception when vehicle not found") {
        val vinSlot = slot<String>()
        val vin = "123"

        every {
            vehicleRepository.findByVin(capture(vinSlot))
        } returns null

        val expectedMessage = "Vehicle with vin '$vin' could not be found."

        shouldThrow<VehicleNotFoundException> {
            vehicleService.findByVin(vin)
        } shouldHaveMessage expectedMessage

        vinSlot.isCaptured shouldBe true
        vinSlot.captured shouldBeEqual vin

        verifyAll {
            vehicleRepository.findByVin(vinSlot.captured)
        }
    }

    test("findByVin has expected results") {
        val vinSlot = slot<String>()
        val vin = "123"
        val vehicle = Vehicle(
            id = 1,
            vin = vin,
            brand = "Mercedes",
            model = "c220d",
            fuelType = FuelType.DIESEL.name,
            mileageUnit = MileageUnit.KM.name,
        )

        every {
            vehicleRepository.findByVin(capture(vinSlot))
        } returns vehicle

        val vehicleDto = vehicleService.findByVin(vin)

        vinSlot.isCaptured shouldBe true
        vinSlot.captured shouldBeEqual vin
        vehicleDto shouldBeEqual vehicle.toVehicleDto()

        verifyAll {
            vehicleRepository.findByVin(vinSlot.captured)
        }
    }

    afterTest {
        clearAllMocks()
    }
})