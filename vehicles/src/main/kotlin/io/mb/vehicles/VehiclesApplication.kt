package io.mb.vehicles

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Main entry point to the application.
 */
@SpringBootApplication
class VehiclesApplication

/**
 * Main method for starting the application.
 */
fun main(args: Array<String>) {
    runApplication<VehiclesApplication>(*args)
}