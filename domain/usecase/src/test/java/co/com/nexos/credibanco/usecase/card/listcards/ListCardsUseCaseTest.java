package co.com.nexos.credibanco.usecase.card.listcards;

import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.card.gateways.CardRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListCardsUseCaseTest {

        @Mock
        private CardRepository cardRepository;
        @InjectMocks
        private ListCardsUseCase listCardsUseCase;

        @Test
        void listCards() {
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

                Card secondCard = Card.builder()
                                .cardId("1000021234567891")
                                .titularName("Erick Diaz")
                                .type("Credit")
                                .productId(100002)
                                .expirationDate(LocalDate.of(2026, 12, 13))
                                .isActivated(false)
                                .isBlocked(false)
                                .balance(0)
                                .build();

                List<Card> cards = Arrays.asList(firstCard, secondCard);
                when(cardRepository.listCards()).thenReturn(Flux.fromIterable(cards));

                Flux<Card> result = listCardsUseCase.listCards();

                StepVerifier.create(result)
                                .expectNextSequence(cards)
                                .expectComplete()
                                .verify();
        }
}