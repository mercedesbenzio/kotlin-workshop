package io.mb.vehicles.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("info.build")
data class BuildInfoProperties(
    val artifact: String,
    val name: String,
    val description: String,
    val version: String
)
