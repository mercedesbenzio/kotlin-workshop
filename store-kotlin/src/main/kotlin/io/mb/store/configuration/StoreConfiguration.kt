package io.mb.store.configuration

import io.mb.store.configuration.properties.BuildInfoProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(BuildInfoProperties::class)
@Configuration
class StoreConfiguration