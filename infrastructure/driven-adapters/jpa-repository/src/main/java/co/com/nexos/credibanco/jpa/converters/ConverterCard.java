package co.com.nexos.credibanco.jpa.converters;

import co.com.nexos.credibanco.jpa.card.CardData;
import co.com.nexos.credibanco.jpa.product.ProductData;
import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.product.Product;

import java.util.List;

public class ConverterCard {
    private ConverterCard() {
        throw new IllegalStateException("Utility Class");
    }

    public static Card convertCardDataToCard(CardData cardData) {
        return cardData!= null ?
                Card.builder()
                .cardId(cardData.getCardId())
                        .type(cardData.getTypeOfCard())
                        .productId(cardData.getProductId())
                        .titularName(cardData.getTitularName())
                        .expirationDate(cardData.getExpirationDate())
                        .isActivated(cardData.getIsActivated())
                        .isBlocked(cardData.getIsBlocked())
                        .balance(cardData.getBalance())

                .build()
               : Card.builder().build();

    }

    public static List<Card> convertCardsDataToCards(List<CardData> cardDataList) {
        return cardDataList.stream().map(ConverterCard::convertCardDataToCard).toList();
    }

    public static CardData convertCardToCardData(Card card) {
        CardData cardData = new CardData();
        if(card!=null){
            cardData.setCardId(card.getCardId());
            cardData.setTypeOfCard(card.getType());
            cardData.setProductId(card.getProductId());
            cardData.setTitularName(card.getTitularName());
            cardData.setExpirationDate(card.getExpirationDate());
            cardData.setIsActivated(card.getIsActivated());
            cardData.setIsBlocked(card.getIsBlocked());
            cardData.setBalance(card.getBalance());
        }
        return cardData;
    }

    public static List<CardData> convertCardsToCardsData(List<Card> cardList) {
        return cardList.stream().map(ConverterCard::convertCardToCardData).toList();
    }
}
