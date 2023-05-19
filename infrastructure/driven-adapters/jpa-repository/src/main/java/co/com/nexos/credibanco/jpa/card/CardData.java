package co.com.nexos.credibanco.jpa.card;

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
@Table(name = "Cards")
public class CardData {
    @Id
    @Column(name = "product_id", nullable = false)
    private String productId;
    @Column(name = "card_id")
    private String cardId;
    @Column(name = "type_of_card")
    private String typeOfCard;
    @Column(name = "document_client", nullable = false)
    private String documentClient;
    @Column(name = "titular_name", nullable = false)
    private String titularName;
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;
    @Column(name = "is_activated", nullable = false)
    private Boolean isActivated;
    @Column(name = "is_blocked", nullable = false)
    private Boolean isBlocked;
    @Column(name = "balance", nullable = false)
    private Integer balance;
}
