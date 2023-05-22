package co.com.nexos.credibanco.usecase.card.generatecardid;

import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.card.TypeOfCard;
import co.com.nexos.credibanco.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GenerateCardIdUseCase {
    private final CardRepository cardRepository;

    public Mono<Card> generateCardId(Integer productId, TypeOfCard typeOfCard){
        return cardRepository.createCard(productId,typeOfCard);
    }
}
