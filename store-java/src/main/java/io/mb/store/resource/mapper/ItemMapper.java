package io.mb.store.resource.mapper;

import io.mb.store.model.Item;
import io.mb.store.resource.dto.ItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDto toItemDto(Item item);
}
