package co.com.nexos.credibanco.jpa.transaction;

import co.com.nexos.credibanco.jpa.card.CardData;
import co.com.nexos.credibanco.jpa.card.CardDataRepository;
import co.com.nexos.credibanco.jpa.converters.ConverterCard;
import co.com.nexos.credibanco.jpa.converters.ConverterTransaction;
import co.com.nexos.credibanco.jpa.helper.AdapterOperations;
import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.transaction.Transaction;
import co.com.nexos.credibanco.model.transaction.gateways.TransactionRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.ResourceAccessException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class TransactionDataRepositoryAdapter extends AdapterOperations<Transaction, TransactionData, String, TransactionDataRepository>
implements TransactionRepository
{
private final CardDataRepository cardDataRepository;
    public TransactionDataRepositoryAdapter(TransactionDataRepository repository, ObjectMapper mapper, CardDataRepository cardDataRepository) {

        super(repository, mapper, d -> mapper.mapBuilder(d, Transaction.TransactionBuilder.class).build());
        this.cardDataRepository = cardDataRepository;
    }

    @Override
    public Mono<Transaction> findTransactionById(String transactionId) {
        return repository.findById(transactionId)
                .map(transactionData ->  Mono.just(ConverterTransaction.convertTransactionDataToTransaction(transactionData)))
                .orElseThrow(()->new ResourceAccessException("No fue posible encontrar la transacción"))
                ;
    }

    @Override
    public Flux<Transaction> listTransactions() {
        List<TransactionData> transactions = (List<TransactionData>) repository.findAll();

        return !transactions.isEmpty() ? Flux.fromIterable(ConverterTransaction.convertTransactionsDataToTransactions(transactions))
                :Flux.empty();
    }

    @Override
    public Mono<Transaction> doPurchase(String cardId,Integer price) {
        Card card = cardDataRepository.findById(cardId).map(ConverterCard::convertCardDataToCard).orElseThrow(()-> new ResourceAccessException("No se encontró la tarjeta de la compra"));
        if(card.getBalance()<price){
            throw new IllegalArgumentException("No cuenta con el saldo suficiente para realizar la compra");
        }
        card.setBalance(card.getBalance()-price);
        cardDataRepository.save(ConverterCard.convertCardToCardData(card));
        Transaction transaction = Transaction.builder()
                .transactionDate(LocalDate.now())
                .card(card)
                .isValid(true)
                .price(price)
                .build();
        TransactionData purchase = repository.save(ConverterTransaction.convertTransactionToTransactionData(transaction));

        return Mono.just(ConverterTransaction.convertTransactionDataToTransaction(purchase));
    }

    @Override
    public Mono<Transaction> reverseTransaction(String cardId, String transactionId) {
        Card card = cardDataRepository.findById(cardId).map(ConverterCard::convertCardDataToCard).orElseThrow(()-> new ResourceAccessException("No se encontró la tarjeta de la compra"));
        Transaction transaction = repository.findById(transactionId).map(ConverterTransaction::convertTransactionDataToTransaction).orElseThrow(()->new ResourceAccessException("No se encontró la compra"));
        transaction.invalidTransaction();
        card.setBalance(card.getBalance()+ transaction.getPrice());
        transaction.setCard(card);
        cardDataRepository.save(ConverterCard.convertCardToCardData(card));
        TransactionData reversedTransaction = repository.save(ConverterTransaction.convertTransactionToTransactionData(transaction));
        return Mono.just(ConverterTransaction.convertTransactionDataToTransaction(reversedTransaction));
    }
}
