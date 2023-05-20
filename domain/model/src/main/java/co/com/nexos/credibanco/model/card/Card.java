package co.com.nexos.credibanco.model.card;
import co.com.nexos.credibanco.model.product.Product;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Card  {

    private String cardId;
    private String type;
    private Product product;
    private String titularName;
    private LocalDate expirationDate;
    private Boolean isActivated;
    private Boolean isBlocked;
    private Integer balance;

}
