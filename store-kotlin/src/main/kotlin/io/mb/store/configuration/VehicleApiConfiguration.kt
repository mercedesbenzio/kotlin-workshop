package io.mb.store.configuration

import io.mb.store.configuration.properties.ExternalApiProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder

@Configuration
class VehicleApiConfiguration {

    private companion object {
        private const val VEHICLE_API_PROPERTIES = "api.vehicle.v1"
    }

    @Bean
    @ConfigurationProperties(VEHICLE_API_PROPERTIES)
    fun vehicleApiProperties() = ExternalApiProperties()

    @Bean
    fun vehicleApiWebClient(
        vehicleApiProperties: ExternalApiProperties
    ) = with(vehicleApiProperties) {
        WebClient.builder().baseUrl(
            UriComponentsBuilder.newInstance()
                .scheme(protocol)
                .host(host)
                .port(port)
                .path(basePath)
                .toUriString()
        ).build()
    }
}