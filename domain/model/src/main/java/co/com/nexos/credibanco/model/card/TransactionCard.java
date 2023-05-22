package co.com.nexos.credibanco.model.card;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TransactionCard {
    private String cardId;
    private Integer price;
}
