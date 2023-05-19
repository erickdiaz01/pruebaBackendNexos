package co.com.nexos.credibanco.model.card;
import co.com.nexos.credibanco.model.product.Product;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Card extends Product {
    enum typeOfCard {
        DEBIT,
        CREDIT
    }
    private String cardId;
    private typeOfCard type;

}
