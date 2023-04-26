package io.mb.store.configuration;

import io.mb.store.configuration.properties.ExternalApiProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class DealerApiConfiguration {

    private static final String DEALER_API_PROPERTIES = "api.dealer.v1";

    @Bean
    @ConfigurationProperties(DEALER_API_PROPERTIES)
    public ExternalApiProperties dealerApiProperties() {
        return new ExternalApiProperties();
    }

    @Bean
    public WebClient dealerApiWebClient(final ExternalApiProperties dealerApiProperties) {
        return WebClient.builder().baseUrl(
                UriComponentsBuilder.newInstance()
                        .scheme(dealerApiProperties.getProtocol())
                        .host(dealerApiProperties.getHost())
                        .port(dealerApiProperties.getPort())
                        .path(dealerApiProperties.getBasePath())
                        .toUriString()
        ).build();
    }
}
