package co.com.nexos.credibanco.jpa.card;

import co.com.nexos.credibanco.jpa.helper.AdapterOperations;
import co.com.nexos.credibanco.jpa.product.ProductData;
import co.com.nexos.credibanco.jpa.product.ProductDataRepository;
import co.com.nexos.credibanco.jpa.services.GeneratorCardNumbers;
import co.com.nexos.credibanco.jpa.services.GlobalExceptionHandler;
import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.card.gateways.CardRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static co.com.nexos.credibanco.jpa.converters.ConverterCard.convertCardDataToCard;
import static co.com.nexos.credibanco.jpa.converters.ConverterCard.convertCardsDataToCards;

@Repository
public class CardDataRepositoryAdapter extends AdapterOperations<Card, CardData, String, CardDataRepository>
 implements CardRepository
{
private final ProductDataRepository productDataRepository;

    public CardDataRepositoryAdapter(CardDataRepository repository, ObjectMapper mapper, ProductDataRepository productDataRepository, GlobalExceptionHandler exceptionHandler) {

        super(repository, mapper, d -> mapper.mapBuilder(d, Card.CardBuilder.class).build());
        this.productDataRepository = productDataRepository;

    }

    @Override
    public Mono<Card> findCardsById(String cardId) {
        Optional <CardData> cardData = repository.findById(cardId);
        return cardData.map(card -> Mono.just(convertCardDataToCard(card))).orElseGet(Mono::empty);
    }

    @Override
    public Flux<Card> listCards() {
        List<CardData> cards = (List<CardData>) repository.findAll();
        return !cards.isEmpty() ? Flux.fromIterable(convertCardsDataToCards(cards))
                :Flux.empty();

    }

    @Override
    public Mono<Card> createCard(String productId, String typeOfCard) {

        ProductData product =  productDataRepository.findById(productId).orElseThrow(()->new ResourceAccessException("Producto no encontrado"));
        try{
            String fullName = product.getClient().getFullName();
            String cardNumber = GeneratorCardNumbers.generate(productId);
            LocalDate expirationDate = LocalDate.now().plusYears(3);
           CardData newCard = repository.save(CardData.builder()
                    .cardId(cardNumber)
                    .titularName(fullName)
                    .balance(0)
                    .productData(product)
                    .typeOfCard(typeOfCard)
                    .expirationDate(expirationDate)
                    .isActivated(false)
                    .isBlocked(false)
                    .build());
           product.setCardData(newCard);
           productDataRepository.save(product);
           return Mono.just(convertCardDataToCard(newCard));
        }catch (Exception e){
            return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error en el repositorio", e));
        }

    }

    @Override
    public Mono<Card> activeCard(String cardId) {
        return repository.findById(cardId).map(cardData -> {
            convertCardDataToCard(cardData).activateCard();
            repository.save(cardData);
            return Mono.just(convertCardDataToCard(cardData));
        }).orElseThrow(()->new ResourceAccessException("No fue posible encontrar la tarjeta"));
    }

    @Override
    public Mono<Card> blockCard(String cardId) {
        return repository.findById(cardId).map(cardData -> {
            convertCardDataToCard(cardData).blockCard();
            repository.save(cardData);
            return Mono.just(convertCardDataToCard(cardData));
        }).orElseThrow(()->new ResourceAccessException("No fue posible encontrar la tarjeta"));
    }

    @Override
    public Mono<Integer> consultBalance(String cardId) {
        return
                repository.findById(cardId).map(cardData ->
                    Mono.just(cardData.getBalance())
                ).orElseThrow(()->new ResourceAccessException("No fue posible encontrar la tarjeta"));
    }

    @Override
    public Mono<Card> rechargeBalance(String cardId, Integer balance) {
        return repository.findById(cardId).map(cardData -> {
            if(balance>0&&cardData.getExpirationDate().isAfter(LocalDate.now())){
                cardData.setBalance(cardData.getBalance()+balance);
                return Mono.just(convertCardDataToCard(cardData));
            }else{
                throw new IllegalArgumentException("No es posible realizar una recarga negativa o nula, o está vencida la tarjeta");
            }
        } ).orElseThrow(()-> new ResourceAccessException("No fue posible encontrar la tarjeta"));
    }
}
