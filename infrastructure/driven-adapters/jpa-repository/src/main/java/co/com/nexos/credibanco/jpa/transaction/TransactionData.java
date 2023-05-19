package co.com.nexos.credibanco.jpa.transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "Transactions")
public class TransactionData {

    @Id
    @Column(name = "transaction_id", nullable = false)
    private String transactionId;
    @Column(name = "card_id", nullable = false)
    private String cardId;
    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;
    @Column(name = "is_valid", nullable = false)
    private Boolean isValid;
    @Column(name = "price", nullable = false)
    private Integer price;
}
