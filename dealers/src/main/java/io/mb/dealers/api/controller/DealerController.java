package io.mb.dealers.api.controller;

import io.mb.dealers.api.mapper.DealerResponseMapper;
import io.mb.dealers.api.model.CreateDealerRequest;
import io.mb.dealers.api.model.DealerResponse;
import io.mb.dealers.service.DealerService;
import io.mb.dealers.service.mapper.DealerMapper;
import io.mb.dealers.service.model.Dealer;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @Slf4j
// @RequiredArgsConstructor
@RestController
@RequestMapping(DealerController.DEALERS_BASE_URL)
@Validated
public class DealerController {

    public static final String DEALERS_BASE_URL = "/dealers";

    private static final String ID_PARAM = "id";

    private static final String DEALERS_BY_ID = "/{" + ID_PARAM + "}";

    private static final Logger log = LogManager.getLogger(DealerController.class);

    private final DealerService dealerService;

    private final DealerMapper dealerMapper;

    private final DealerResponseMapper dealerResponseMapper;

    @Autowired
    public DealerController(
            final DealerService dealerService,
            final DealerMapper dealerMapper,
            final DealerResponseMapper dealerResponseMapper
    ) {
        this.dealerService = dealerService;
        this.dealerMapper = dealerMapper;
        this.dealerResponseMapper = dealerResponseMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DealerResponse create(@Valid @RequestBody final CreateDealerRequest createDealerRequest) {

        log.info("Creating a new dealer");

        return dealerMapper.mapToDealer(createDealerRequest).map(dealer -> {
            final var savedDealer = dealerService.save(dealer);
            log.info("Successfully created a new dealer '{}'", savedDealer);
            return savedDealer;
        }).flatMap(dealerResponseMapper::mapToDealerResponse).orElseThrow(IllegalStateException::new);
    }

    @GetMapping
    public List<DealerResponse> findAll() {

        log.info("Fetching all dealers");

        final List<Dealer> dealers = dealerService.findAll();

        log.info("Returning {} dealers", dealers.size());

        return dealerResponseMapper.mapToDealerResponses(dealers);
    }

    @GetMapping(DEALERS_BY_ID)
    public DealerResponse findById(@PathVariable(ID_PARAM) final String id) {

        log.info("Fetching dealer with id '{}'", id);

        final Dealer dealer = dealerService.findById(id);

        log.info("Found dealer '{}'", dealer);

        return dealerResponseMapper.mapToDealerResponse(dealer).orElseThrow(IllegalStateException::new);
    }
}
