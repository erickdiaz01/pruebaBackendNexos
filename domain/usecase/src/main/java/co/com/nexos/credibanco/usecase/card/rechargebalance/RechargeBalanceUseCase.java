package co.com.nexos.credibanco.usecase.card.rechargebalance;

import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.card.gateways.CardRepository;
import co.com.nexos.credibanco.model.product.Product;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RechargeBalanceUseCase {
    private final CardRepository cardRepository;

    public Mono<Card> rechargeBalance(String cardId, Integer balance){
        return cardRepository.rechargeBalance(cardId,balance);
    }
}
