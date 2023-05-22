package co.com.nexos.credibanco.usecase.card.listcards;

import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ListCardsUseCase {
    private final CardRepository cardRepository;

    public Flux<Card> listCards(){
        return cardRepository.listCards();
    }
}
