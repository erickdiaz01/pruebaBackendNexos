package co.com.nexos.credibanco.api;


import co.com.nexos.credibanco.model.transaction.Transaction;
import co.com.nexos.credibanco.usecase.transaction.consulttransaction.ConsultTransactionUseCase;
import co.com.nexos.credibanco.usecase.transaction.purchasetransaction.PurchaseTransactionUseCase;
import co.com.nexos.credibanco.usecase.transaction.reversetransaction.ReverseTransactionUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/api/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionRest {

    private final ConsultTransactionUseCase consultTransactionUseCase;
    private final PurchaseTransactionUseCase purchaseTransactionUseCase;
    private final ReverseTransactionUseCase reverseTransactionUseCase;
    @PostMapping("/purchase")
    public Mono<Transaction> purchaseTransaction(@RequestBody String cardId, @RequestBody Integer price){
        return purchaseTransactionUseCase.doTransaction(cardId,price);
    }
    @GetMapping("/{transactionId}")
    public Mono<Transaction> consultTransaction(@PathVariable String transactionId){
        return consultTransactionUseCase.consultTransaction(transactionId);
    }
    @PostMapping("/anulation")
    public Mono<Transaction> anulateTransaction(@RequestBody String transactionId,@RequestBody String cardId){
        return reverseTransactionUseCase.reverserTransaction(transactionId,cardId);
    }
}
