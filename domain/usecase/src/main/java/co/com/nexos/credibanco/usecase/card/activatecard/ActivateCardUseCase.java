package co.com.nexos.credibanco.usecase.card.activatecard;

import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ActivateCardUseCase {
    private final CardRepository cardRepository;

    public Mono<Card> activateCard(String cardId){
        return cardRepository.activeCard(cardId);
    }
}
