package co.com.nexos.credibanco.model.transaction;

import co.com.nexos.credibanco.model.card.Card;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Transaction {
    private Integer transactionId;
    private Card card;
    private LocalDate transactionDate;
    private Boolean isValid;
    private Integer price;

    public void invalidTransaction() {
        if (this.isValid) {
            this.setIsValid(false);
        }
    }
}
