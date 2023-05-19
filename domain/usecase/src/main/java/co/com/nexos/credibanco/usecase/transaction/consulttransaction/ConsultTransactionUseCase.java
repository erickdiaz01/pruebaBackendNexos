package co.com.nexos.credibanco.usecase.transaction.consulttransaction;

import co.com.nexos.credibanco.model.transaction.Transaction;
import co.com.nexos.credibanco.model.transaction.gateways.TransactionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ConsultTransactionUseCase {
    private TransactionRepository transactionRepository;

    public Mono<Transaction> consultTransaction(String transactionId){
        return  transactionRepository.findTransactionById(transactionId);
    }
}
