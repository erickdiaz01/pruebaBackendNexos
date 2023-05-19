package co.com.nexos.credibanco.model.transaction.gateways;

import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.transaction.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionRepository {
    Mono<Transaction> findTransactionById (String transactionId);
    Flux<Transaction> listTransactions();
    Mono<Transaction> doPurchase(String cardId, Integer price);
    Mono<Transaction> reverseTransaction(String transactionId, String cardId);
}
