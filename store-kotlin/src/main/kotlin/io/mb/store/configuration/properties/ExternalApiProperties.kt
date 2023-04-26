package io.mb.store.configuration.properties

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

class ExternalApiProperties {
    @field:NotBlank
    lateinit var host: String

    @field:NotBlank
    lateinit var basePath: String

    @field:Pattern(regexp = "https?")
    var protocol: String = "https"

    @field:Min(0)
    var port: Int = 443
}