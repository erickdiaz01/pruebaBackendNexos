package co.com.nexos.credibanco.usecase.card.consultbalance;

import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ConsultBalanceUseCase {
    private final CardRepository cardRepository;

    public Mono<Integer> consultBalance(String cardId){
        return cardRepository.consultBalance(cardId);
    }
}

