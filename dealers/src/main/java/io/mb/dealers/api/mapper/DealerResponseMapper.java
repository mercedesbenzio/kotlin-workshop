package io.mb.dealers.api.mapper;

import io.mb.dealers.api.model.DealerResponse;
import io.mb.dealers.service.model.Dealer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DealerResponseMapper {

    public Optional<DealerResponse> mapToDealerResponse(final Dealer dealer) {

        // TODO (DONE) Optimize the method below
        // Can you do this better without using the if statement?
        // ANSWER Yes we can by using Optional

        return Optional.ofNullable(dealer)
                .map(x -> new DealerResponse(x.id(), x.name(), x.description()));
    }

    public List<DealerResponse> mapToDealerResponses(final List<Dealer> dealers) {

        return dealers.stream().flatMap(dealer -> mapToDealerResponse(dealer).stream()).toList();

        /*
        // A more verbose approach
        final List<DealerResponse> dealerResponses = new ArrayList<>();

        for (Dealer dealer : dealers) {

            mapToDealerResponse(dealer).ifPresent(dealerResponses::add);
        }

        return dealerResponses;
        */
    }
}
