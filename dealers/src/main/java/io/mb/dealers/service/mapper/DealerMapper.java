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

        // TODO Optimize the method below
        // Can you do this better without using the if statement?

        Dealer result = null;

        if (createDealerRequest != null) {

            result = Dealer.builder()
                    .id(createDealerRequest.id())
                    .name(createDealerRequest.name())
                    .description(createDealerRequest.description())
                    .build();
        }

        return Optional.ofNullable(result);
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
