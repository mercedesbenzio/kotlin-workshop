package io.mb.store.resource.mapper

import io.mb.store.model.Item
import io.mb.store.resource.dto.ItemDto

fun Item.toItemDto(): ItemDto =
    ItemDto(
        id = checkNotNull(id),
        vin = vin,
        dealerId = dealerId
    )
