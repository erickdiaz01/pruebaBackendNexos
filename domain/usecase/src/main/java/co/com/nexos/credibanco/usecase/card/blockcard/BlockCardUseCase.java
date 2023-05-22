package co.com.nexos.credibanco.usecase.card.blockcard;

import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BlockCardUseCase {
    private final CardRepository cardRepository;

    public Mono<Card> blockCard(String cardId) {
        return cardRepository.blockCard(cardId);
    }
}
