package co.com.nexos.credibanco.usecase.transaction.purchasetransaction;

import co.com.nexos.credibanco.model.transaction.Transaction;
import co.com.nexos.credibanco.model.transaction.gateways.TransactionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PurchaseTransactionUseCase {

    private TransactionRepository transactionRepository;

    public Mono<Transaction> doTransaction(String cardId,Integer price){
        return  transactionRepository.doPurchase(cardId,price);
    }
}
