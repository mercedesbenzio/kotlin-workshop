package io.mb.dealers.configuration;

import io.mb.dealers.configuration.properties.BuildInfoProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfiguration {

    private final BuildInfoProperties buildInfoProperties;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info().version(buildInfoProperties.version())
                        .title(buildInfoProperties.name())
                        .description(buildInfoProperties.description())
        );
    }
}
