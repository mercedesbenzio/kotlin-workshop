package io.mb.store.service

import io.mb.store.model.Item
import io.mb.store.repository.StoreRepository
import io.mb.store.resource.dto.ItemCreateDto
import io.mb.store.resource.dto.ItemDto
import io.mb.store.resource.mapper.toItem
import io.mb.store.resource.mapper.toItemDto
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val storeRepository: StoreRepository,
) {

    fun findAll(): List<ItemDto> =
        storeRepository.findAll().map(Item::toItemDto)

    fun findByDealerId(dealerId: String): List<ItemDto> =
        storeRepository.findByDealerId(dealerId)
            .map(Item::toItemDto)

    /**
     * TODO (DONE) Implement the method below
     *
     * This method should return all items grouped by dealer id
     */
    fun findAllGroupedByDealerId(): Map<String, List<ItemDto>> =
        storeRepository.findAll().asSequence()
            .map(Item::toItemDto)
            .groupBy { it.dealerId }

    @Transactional
    fun save(itemCreateDto: ItemCreateDto): ItemDto =
        storeRepository.save(itemCreateDto.toItem()).toItemDto()
}