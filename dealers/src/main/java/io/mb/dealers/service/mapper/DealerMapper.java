package io.mb.dealers.service.mapper;

import io.mb.dealers.api.model.CreateDealerRequest;
import io.mb.dealers.repository.DealerEntity;
import io.mb.dealers.service.model.Dealer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DealerMapper {

    public Optional<Dealer> mapToDealer(final CreateDealerRequest createDealerRequest) {

        // TODO (DONE) Optimize the method below
        // Can you do this better without using the if statement?
        // ANSWER Yes we can by using Optional :)

        return Optional.ofNullable(createDealerRequest)
                .map(x ->
                        Dealer.builder()
                                .id(x.id())
                                .name(x.name())
                                .description(x.description())
                                .build()
                );
    }

    public Dealer mapToDealer(final DealerEntity dealerEntity) {

        return Dealer.builder()
                .id(dealerEntity.getDealerId())
                .name(dealerEntity.getName())
                .description(dealerEntity.getDescription())
                .build();
    }

    public List<Dealer> mapToDealers(final List<DealerEntity> dealerEntities) {

        return dealerEntities.stream().map(this::mapToDealer).toList();

        /*
        // A more verbose approach

        final List<Dealer> result = new ArrayList<>();

        for (DealerEntity dealerEntity : dealerEntities) {

            result.add(mapToDealer(dealerEntity));
        }

        return result;
        */
    }
}
