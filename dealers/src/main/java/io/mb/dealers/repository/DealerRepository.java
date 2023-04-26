package io.mb.dealers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DealerRepository extends JpaRepository<DealerEntity, String> {

    Optional<DealerEntity> findByDealerId(String dealerId);
}
