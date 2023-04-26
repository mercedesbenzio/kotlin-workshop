package io.mb.dealers.service;

import io.mb.dealers.api.exception.DealerAlreadyExistsException;
import io.mb.dealers.repository.DealerEntity;
import io.mb.dealers.repository.DealerRepository;
import io.mb.dealers.service.mapper.DealerMapper;
import io.mb.dealers.service.model.Dealer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerServiceTest {

    @Mock
    private DealerRepository dealerRepository;
    @Mock
    private DealerMapper dealerMapper;
    @InjectMocks
    private DealerService dealerService;

    @Test
    void save_throws_exception_when_dealer_already_exists() {
        final var dealerId = "1234";

        final DealerEntity dealerEntity = DealerEntity.builder()
                .dealerId(dealerId)
                .name("a_dealer")
                .build();

        Mockito.when(dealerRepository.findByDealerId(Mockito.anyString()))
                .thenReturn(Optional.ofNullable(dealerEntity));

        final Dealer dealer = Dealer.builder()
                .id(dealerId)
                .name("other_dealer")
                .build();

        final String expectedMessage = "Dealer with id '" + dealerId + "' already exists";

        Assertions.assertThatExceptionOfType(DealerAlreadyExistsException.class)
                .isThrownBy(() -> dealerService.save(dealer))
                .withMessage(expectedMessage);

        final var dealerIdCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(dealerRepository).findByDealerId(dealerIdCaptor.capture());
        Mockito.verifyNoMoreInteractions(dealerRepository);
        Mockito.verifyNoInteractions(dealerMapper);

        Assertions.assertThat(dealerIdCaptor.getValue()).isEqualTo(dealerId);
    }

    @Test
    void save_is_persisted_when_dealer_does_not_exist() {
        final String dealerId = "1234";

        Mockito.when(dealerRepository.findByDealerId(Mockito.anyString()))
                .thenReturn(Optional.empty());

        final DealerEntity dealerEntity = Mockito.mock(DealerEntity.class);

        Mockito.when(dealerRepository.save(Mockito.any()))
                .thenReturn(dealerEntity);

        final Dealer dealer = Dealer.builder()
                .id(dealerId)
                .name("a_dealer")
                .build();

        Mockito.when(dealerMapper.mapToDealer(Mockito.any(DealerEntity.class)))
                .thenReturn(dealer);

        final Dealer savedDealer = dealerService.save(dealer);

        final var dealerIdCaptor = ArgumentCaptor.forClass(String.class);
        final var dealerEntityCaptor = ArgumentCaptor.forClass(DealerEntity.class);

        Mockito.verify(dealerRepository).findByDealerId(dealerIdCaptor.capture());
        Mockito.verify(dealerMapper).mapToDealer(dealerEntityCaptor.capture());
        Mockito.verifyNoMoreInteractions(dealerRepository, dealerMapper);

        Assertions.assertThat(dealerIdCaptor.getValue()).isEqualTo(dealerId);
        Assertions.assertThat(dealerEntityCaptor.getValue()).isEqualTo(dealerEntity);

        Assertions.assertThat(savedDealer).isEqualTo(dealer);
    }
}