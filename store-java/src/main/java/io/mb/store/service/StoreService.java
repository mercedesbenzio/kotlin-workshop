package io.mb.store.service;

import io.mb.store.model.Item;
import io.mb.store.repository.StoreRepository;
import io.mb.store.resource.dto.ItemCreateDto;
import io.mb.store.resource.dto.ItemDto;
import io.mb.store.resource.mapper.ItemCreateDtoMapper;
import io.mb.store.resource.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final ItemMapper itemMapper;
    private final ItemCreateDtoMapper itemCreateDtoMapper;

    public List<ItemDto> findAll() {
        return storeRepository.findAll().stream()
                .map(itemMapper::toItemDto).toList();
    }

    public List<ItemDto> findByDealerId(final String dealerId) {
        return storeRepository.findByDealerId(dealerId).stream()
                .map(itemMapper::toItemDto).toList();
    }

    public Map<String, List<ItemDto>> finAllGroupedByDealerId() {
        return storeRepository.findAll().stream()
                .map(itemMapper::toItemDto)
                .collect(Collectors.groupingBy(ItemDto::dealerId));
    }

    @Transactional
    public ItemDto save(final ItemCreateDto itemCreateDto) {
        Item savedItem = storeRepository.save(itemCreateDtoMapper.mapToItem(itemCreateDto));
        return itemMapper.toItemDto(savedItem);
    }
}
