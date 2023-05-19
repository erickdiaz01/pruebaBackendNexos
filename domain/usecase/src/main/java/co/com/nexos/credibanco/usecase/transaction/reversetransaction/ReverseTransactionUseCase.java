package co.com.nexos.credibanco.usecase.transaction.reversetransaction;

import co.com.nexos.credibanco.model.transaction.Transaction;
import co.com.nexos.credibanco.model.transaction.gateways.TransactionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReverseTransactionUseCase {
    private TransactionRepository transactionRepository;

    public Mono<Transaction> reverserTransaction(String transactionId, String cardId){
        return  transactionRepository.reverseTransaction(transactionId, cardId);
    }
}
