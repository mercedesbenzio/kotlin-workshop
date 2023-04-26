package io.mb.store.controller

import io.mb.store.resource.ITEMS_MAPPING
import io.mb.store.resource.dto.ItemCreateDto
import io.mb.store.resource.dto.ItemDto
import io.mb.store.service.StoreService
import jakarta.validation.Valid
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(ITEMS_MAPPING)
class ItemsController(
    private val storeService: StoreService,
) {

    private companion object {
        private val logger = KotlinLogging.logger { }
    }

    @GetMapping
    fun findAll(): List<ItemDto> {
        logger.info { "Fetching all items in store" }
        return storeService.findAll().also {
            logger.info { "Returned all items in store" }
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody itemCreateDto: ItemCreateDto): ItemDto {
        logger.info { "Adding entry $itemCreateDto to the store" }
        return storeService.save(itemCreateDto).also {
            logger.info { "Entry $it created and added to the store" }
        }
    }
}