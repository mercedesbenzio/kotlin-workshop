package io.mb.dealers.service;

import io.mb.dealers.api.exception.DealerAlreadyExistsException;
import io.mb.dealers.api.exception.DealerNotFoundException;
import io.mb.dealers.repository.DealerEntity;
import io.mb.dealers.repository.DealerRepository;
import io.mb.dealers.service.mapper.DealerMapper;
import io.mb.dealers.service.model.Dealer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealerService {

    private final DealerRepository dealerRepository;

    private final DealerMapper dealerMapper;

    @Transactional
    public Dealer save(final Dealer dealer) {

        // TODO Check the question below (you can leave this one for last)
        // Do you think this check is enough to ensure that
        // wel will not have two dealers with the same dealerId persisted in the DB?
        // Can we use other method instead of find?

        dealerRepository.findByDealerId(dealer.id()).ifPresent(dealerEntity -> {
            throw new DealerAlreadyExistsException(dealerEntity.getDealerId());
        });

        final var dealerEntityToSave = DealerEntity.builder()
                .dealerId(dealer.id())
                .name(dealer.name())
                .description(dealer.description())
                .build();

        final var savedDealerEntity = dealerRepository.save(dealerEntityToSave);

        return dealerMapper.mapToDealer(savedDealerEntity);
    }

    public List<Dealer> findAll() {

        final var dealerEntities = dealerRepository.findAll();

        return dealerMapper.mapToDealers(dealerEntities);
    }

    public Dealer findById(final String id) {

        final DealerEntity dealerEntity = dealerRepository.findByDealerId(id)
                .orElseThrow(() -> new DealerNotFoundException(id));

        return dealerMapper.mapToDealer(dealerEntity);
    }
}
