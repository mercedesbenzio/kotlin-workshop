package io.mb.vehicles.resource.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class VehicleNotFoundException(vin: String) :
    RuntimeException("Vehicle with vin '$vin' could not be found.")