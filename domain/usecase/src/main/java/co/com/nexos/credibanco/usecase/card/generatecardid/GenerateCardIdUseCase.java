package co.com.nexos.credibanco.usecase.card.generatecardid;

import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.card.gateways.CardRepository;
import co.com.nexos.credibanco.model.product.Product;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
public class GenerateCardIdUseCase {
    private final CardRepository cardRepository;

    public Mono<Card> generateCardId(String productId, String typeOfCard){
        return cardRepository.createCard(productId,typeOfCard);
    }
}
