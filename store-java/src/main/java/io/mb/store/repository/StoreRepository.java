package io.mb.store.repository;

import io.mb.store.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Item, String> {
    List<Item> findByDealerId(String dealerId);
}
