package co.com.nexos.credibanco.model.transaction;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Transaction {
    private String transactionId;
    private String cardId;
    private LocalDate transactionDate;
    private Boolean isValid;
    private Integer price;
}
