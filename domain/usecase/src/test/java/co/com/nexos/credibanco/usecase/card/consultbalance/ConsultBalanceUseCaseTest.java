package co.com.nexos.credibanco.usecase.card.consultbalance;

import co.com.nexos.credibanco.model.card.Card;

import co.com.nexos.credibanco.model.card.gateways.CardRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultBalanceUseCaseTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private ConsultBalanceUseCase consultBalanceUseCase;

    @Test
    void consultBalance() {
        Card firstCard = Card.builder()
                .cardId("1000011234567890")
                .titularName("Erick Diaz")
                .type("Debit")
                .productId(100001)
                .expirationDate(LocalDate.of(2024, 1, 3))
                .isActivated(true)
                .isBlocked(false)
                .balance(1200)
                .build();
        when(cardRepository.consultBalance("1000011234567890")).thenReturn(Mono.just(1200));

        Mono<Integer> result = consultBalanceUseCase.consultBalance("1000011234567890");

        StepVerifier.create(result)
                .expectNextMatches(integer -> firstCard.getBalance().equals(integer))
                .expectComplete()
                .verify();

    }
}