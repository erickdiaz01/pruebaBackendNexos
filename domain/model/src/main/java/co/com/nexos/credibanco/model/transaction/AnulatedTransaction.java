package co.com.nexos.credibanco.model.transaction;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AnulatedTransaction {
    private String cardId;
    private Integer transactionId;
}
