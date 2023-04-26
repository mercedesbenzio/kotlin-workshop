package io.mb.store.configuration

import io.mb.store.configuration.properties.BuildInfoProperties
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfiguration(private val buildInfoProperties: BuildInfoProperties) {

    @Bean
    fun openAPI(): OpenAPI = with(buildInfoProperties) {
        OpenAPI().info(Info().version(version).title(name).description(description))
    }
}
