package co.com.nexos.credibanco.usecase.card.generatecardid;

import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.card.TypeOfCard;
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
class GenerateCardIdUseCaseTest {

        @Mock
        private CardRepository cardRepository;
        @InjectMocks
        private GenerateCardIdUseCase generateCardIdUseCase;

        @Test
        void generateCardId() {
                TypeOfCard type = TypeOfCard.builder().type("Debit").build();
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

                when(cardRepository.createCard(100001, type)).thenReturn(Mono.just(firstCard));

                Mono<Card> result = generateCardIdUseCase.generateCardId(100001, type);

                StepVerifier.create(result)
                                .expectNextMatches(card -> card.getCardId().equals(firstCard.getCardId())
                                                && card.getIsActivated().equals(firstCard.getIsActivated()))
                                .expectComplete()
                                .verify();

        }
}