package io.mb.store.controller;

import io.mb.store.resource.dto.ItemCreateDto;
import io.mb.store.resource.dto.ItemDto;
import io.mb.store.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.mb.store.resource.Constants.ITEMS_MAPPING;

@RestController
@RequestMapping(ITEMS_MAPPING)
@RequiredArgsConstructor
@Slf4j
@Validated
public class ItemsController {
    private final StoreService storeService;

    @GetMapping
    public List<ItemDto> findAll() {
        log.info("Fetching all items in store");
        final List<ItemDto> itemDtos = storeService.findAll();
        log.info("Returned all items in store");
        return itemDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto create(@Valid @RequestBody final ItemCreateDto itemCreateDto) {
        log.info("Adding entry " + itemCreateDto + " to the store");
        ItemDto itemDto = storeService.save(itemCreateDto);
        log.info("Entry " + itemDto + " created and added to the store");
        return itemDto;
    }
}
