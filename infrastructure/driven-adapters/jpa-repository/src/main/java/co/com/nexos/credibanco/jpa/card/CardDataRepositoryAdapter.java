package co.com.nexos.credibanco.jpa.card;

import co.com.nexos.credibanco.jpa.helper.AdapterOperations;
import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.card.gateways.CardRepository;
import co.com.nexos.credibanco.model.client.Client;
import co.com.nexos.credibanco.model.product.Product;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CardDataRepositoryAdapter extends AdapterOperations<Card/* change for domain model */, CardData/* change for adapter model */, String, CardDataRepository>
 implements CardRepository
{

    public CardDataRepositoryAdapter(CardDataRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.mapBuilder(d, Card.CardBuilder.class/* change for domain model */).build());
    }

    @Override
    public Mono<Card> findCardsById(String cardId) {
        return null;
    }

    @Override
    public Flux<Card> listCards() {
        return null;
    }

    @Override
    public Mono<Card> createCard(Product product, String typeOfCard) {
        return null;
    }

    @Override
    public Mono<Card> activeCard(String cardId) {
        return null;
    }

    @Override
    public Mono<Card> blockCard(String cardId) {
        return null;
    }

    @Override
    public Mono<Integer> consultBalance(String cardId) {
        return null;
    }

    @Override
    public Mono<Card> rechargeBalance(String cardId, Integer balance) {
        return null;
    }
}
