package co.com.nexos.credibanco.jpa.transaction;

import co.com.nexos.credibanco.jpa.helper.AdapterOperations;
import co.com.nexos.credibanco.model.transaction.Transaction;
import co.com.nexos.credibanco.model.transaction.gateways.TransactionRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TransactionDataRepositoryAdapter extends AdapterOperations<Transaction/* change for domain model */, TransactionData/* change for adapter model */, String, TransactionDataRepository>
implements TransactionRepository
{

    public TransactionDataRepositoryAdapter(TransactionDataRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.map(d, Transaction.class/* change for domain model */));
    }

    @Override
    public Mono<Transaction> findTransactionById(String transactionId) {
        return null;
    }

    @Override
    public Flux<Transaction> listTransactions() {
        return null;
    }

    @Override
    public Mono<Transaction> doPurchase(String cardId,Integer price) {
        return null;
    }

    @Override
    public Mono<Transaction> reverseTransaction(String cardId, String transactionId) {
        return null;
    }
}
