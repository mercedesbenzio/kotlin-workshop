package io.mb.dealers.configuration;

import io.mb.dealers.configuration.properties.BuildInfoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(BuildInfoProperties.class)
@Configuration
public class DealerApplicationConfiguration {
}
