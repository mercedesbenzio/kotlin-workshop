package io.mb.store.resource.mapper

import io.mb.store.model.Item
import io.mb.store.resource.dto.ItemCreateDto

fun ItemCreateDto.toItem(): Item =
    Item(
        vin = vin,
        dealerId = dealerId
    )
