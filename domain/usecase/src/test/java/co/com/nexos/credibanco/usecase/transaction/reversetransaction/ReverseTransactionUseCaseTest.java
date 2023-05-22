package co.com.nexos.credibanco.usecase.transaction.reversetransaction;

import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.transaction.Transaction;
import co.com.nexos.credibanco.model.transaction.gateways.TransactionRepository;
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
class ReverseTransactionUseCaseTest {
        @Mock
        private TransactionRepository transactionRepository;

        @InjectMocks
        private ReverseTransactionUseCase reverseTransactionUseCase;

        @Test
        void reverserTransaction() {
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

                Transaction transaction = Transaction.builder()
                                .transactionId(1)
                                .card(firstCard)
                                .transactionDate(LocalDate.of(2022, 4, 10))
                                .isValid(false)
                                .price(1200)
                                .build();

                when(transactionRepository.reverseTransaction("1000011234567890", 1))
                                .thenReturn(Mono.just(transaction));

                Mono<Transaction> result = reverseTransactionUseCase.reverserTransaction("1000011234567890", 1);

                StepVerifier.create(result)
                                .expectNextMatches(
                                                transaction1 -> transaction1.getIsValid().equals(false)
                                                                && transaction1.getCard().getCardId()
                                                                                .equals("1000011234567890"))

                                .expectComplete()
                                .verify();
        }
}