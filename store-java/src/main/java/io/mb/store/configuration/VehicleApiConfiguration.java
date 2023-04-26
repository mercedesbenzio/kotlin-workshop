package io.mb.store.configuration;

import io.mb.store.configuration.properties.ExternalApiProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class VehicleApiConfiguration {

    private static final String VEHICLE_API_PROPERTIES = "api.vehicle.v1";

    @Bean
    @ConfigurationProperties(VEHICLE_API_PROPERTIES)
    public ExternalApiProperties vehicleApiProperties() {
        return new ExternalApiProperties();
    }

    @Bean
    public WebClient vehicleApiWebClient(final ExternalApiProperties vehicleApiProperties) {
        return WebClient.builder().baseUrl(
                UriComponentsBuilder.newInstance()
                        .scheme(vehicleApiProperties().getProtocol())
                        .host(vehicleApiProperties.getHost())
                        .port(vehicleApiProperties.getPort())
                        .path(vehicleApiProperties.getBasePath())
                        .toUriString()
        ).build();
    }
}
