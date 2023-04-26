package io.mb.store.resource.mapper;

import io.mb.store.model.Item;
import io.mb.store.resource.dto.ItemCreateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemCreateDtoMapper {
    Item mapToItem(ItemCreateDto itemCreateDto);
}
