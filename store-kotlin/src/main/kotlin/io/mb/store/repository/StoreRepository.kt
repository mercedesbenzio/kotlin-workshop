package io.mb.store.repository

import io.mb.store.model.Item
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository : JpaRepository<Item, String> {
    fun findByDealerId(dealerId: String): List<Item>
}