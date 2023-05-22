package co.com.nexos.credibanco.model.card.gateways;

import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.card.TypeOfCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CardRepository {
    Mono<Card> findCardsById(String cardId);

    Flux<Card> listCards();

    Mono<Card> createCard(Integer productId, TypeOfCard typeOfCard);

    Mono<Card> activeCard(String cardId);

    Mono<Card> blockCard(String cardId);

    Mono<Integer> consultBalance(String cardId);

    Mono<Card> rechargeBalance(String cardId, Integer balance);
}
