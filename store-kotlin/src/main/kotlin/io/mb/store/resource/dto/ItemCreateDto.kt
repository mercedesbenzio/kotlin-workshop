package io.mb.store.resource.dto

import jakarta.validation.constraints.NotBlank

data class ItemCreateDto(
    @field:NotBlank
    val vin: String,
    @field:NotBlank
    val dealerId: String
)