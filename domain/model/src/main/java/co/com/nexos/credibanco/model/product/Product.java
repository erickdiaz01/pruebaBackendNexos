package co.com.nexos.credibanco.model.product;
import co.com.nexos.credibanco.model.client.Client;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {
    private Integer productId;
    private Client client;
    private String cardId;
}
