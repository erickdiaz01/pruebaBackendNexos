package co.com.nexos.credibanco.jpa.card;

import co.com.nexos.credibanco.jpa.product.ProductData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
@Table(name = "Cards")
public class CardData {
    @Id
    @Column(name = "card_id", nullable = false)
    private String cardId;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ProductData productData;

    @Column(name = "type_of_card")
    @Size(max = 10, message = "Limite máximo de 50 caracteres")
    @Pattern(regexp = "[^0-9]*", message = "El campo no debe contener números")
    private String typeOfCard;


    @Column(name = "titular_name", nullable = false)
    @Size(max = 50, message = "Limite máximo de 50 caracteres")
    @Pattern(regexp = "[^0-9]*", message = "El campo no debe contener números")
    private String titularName;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "is_activated", nullable = false)
    private Boolean isActivated;
    @Column(name = "is_blocked", nullable = false)
    private Boolean isBlocked;

    @Min(0)
    @Column(name = "balance", nullable = false)
    private Integer balance;
}
