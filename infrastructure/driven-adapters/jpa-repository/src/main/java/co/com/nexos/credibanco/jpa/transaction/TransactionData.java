package co.com.nexos.credibanco.jpa.transaction;

import co.com.nexos.credibanco.jpa.card.CardData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "Transactions")
public class TransactionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "transactions_SEQ")
    @SequenceGenerator(name = "transactions_SEQ", sequenceName = "transactions_seq", allocationSize = 1)
    @Column(name = "transaction_id", nullable = false)
    private Integer transactionId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "card_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CardData cardData;
    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;
    @Column(name = "is_valid", nullable = false)
    private Boolean isValid;

    @Column(name = "price", nullable = false)
    @Min(0)
    private Integer price;
}
