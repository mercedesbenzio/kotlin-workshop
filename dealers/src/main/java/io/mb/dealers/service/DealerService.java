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

        // TODO (DONE) Check the question below (you can leave this one for last)
        // Do you think this check is enough to ensure that
        // wel will not have two dealers with the same dealerId persisted in the DB?
        // Can we use other method instead of find?
        //
        // ANSWERS
        // Regarding the first question, the check is not enough in a concurrent
        // scenario, using the default isolation level of transactional. In case
        // of to threads executing this method at the same time they will
        // potentially reach the same conclusion that a dealer does not exist,
        // and later they will both create a dealer with the same id. Meaning
        // that we will have 2 dealers in our database with the same id, which
        // is not what we want. To avoid that we can simply add a unique
        // constraint to our dealerId column.
        //
        // Regarding the second question yes we can define and use an exists
        // function.

        if (dealerRepository.existsByDealerId(dealer.id())) {
            throw new DealerAlreadyExistsException(dealer.id());
        }

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
