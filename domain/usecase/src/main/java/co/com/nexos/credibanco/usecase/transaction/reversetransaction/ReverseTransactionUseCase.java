package co.com.nexos.credibanco.usecase.transaction.reversetransaction;

import co.com.nexos.credibanco.model.transaction.Transaction;
import co.com.nexos.credibanco.model.transaction.gateways.TransactionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReverseTransactionUseCase {
    private final TransactionRepository transactionRepository;

    public Mono<Transaction> reverserTransaction( String cardId,Integer transactionId){
        return  transactionRepository.reverseTransaction(cardId, transactionId);
    }
}
