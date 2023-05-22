package co.com.nexos.credibanco.usecase.card.rechargebalance;

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
class RechargeBalanceUseCaseTest {

    @Mock
    private CardRepository cardRepository;
    @InjectMocks
    private RechargeBalanceUseCase rechargeBalanceUseCase;

    @Test
    void rechargeBalance() {
        Card secondCard = Card.builder()
                .cardId("1000011234567890")
                .titularName("Erick Diaz")
                .type("Debit")
                .productId(100001)
                .expirationDate(LocalDate.of(2024, 1, 3))
                .isActivated(true)
                .isBlocked(false)
                .balance(2700)
                .build();
        when(cardRepository.rechargeBalance("1000011234567890", 1500)).thenReturn(Mono.just(secondCard));

        Mono<Card> result = rechargeBalanceUseCase.rechargeBalance("1000011234567890", 1500);

        StepVerifier.create(result)
                .expectNextMatches(card -> card.getBalance().equals(secondCard.getBalance())
                        && card.getIsActivated().equals(secondCard.getIsActivated()))
                .expectComplete()
                .verify();
    }
}