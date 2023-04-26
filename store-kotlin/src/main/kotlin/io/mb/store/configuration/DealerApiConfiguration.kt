package io.mb.store.configuration

import io.mb.store.configuration.properties.ExternalApiProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder

@Configuration
class DealerApiConfiguration {

    private companion object {
        private const val DEALER_API_PROPERTIES = "api.dealer.v1"
    }

    @Bean
    @ConfigurationProperties(DEALER_API_PROPERTIES)
    fun dealerApiProperties() = ExternalApiProperties()

    @Bean
    fun dealerApiWebClient(
        dealerApiProperties: ExternalApiProperties
    ) = with(dealerApiProperties) {
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