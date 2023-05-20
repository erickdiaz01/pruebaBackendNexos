package co.com.nexos.credibanco.model.product;
import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.client.Client;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {
    private String productId;
    private Client client;
    private Card card;
}
