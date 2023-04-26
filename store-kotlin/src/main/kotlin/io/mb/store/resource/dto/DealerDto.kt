package io.mb.store.resource.dto

// The dealer api has a specification that follows
// openapi standard and in a real world we would have
// these DTOs generated from the API

data class DealerDto(
    val id: String,
    val name: String,
    val description: String?
)