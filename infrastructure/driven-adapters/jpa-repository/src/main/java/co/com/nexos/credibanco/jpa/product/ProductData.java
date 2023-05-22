package co.com.nexos.credibanco.jpa.product;

import co.com.nexos.credibanco.jpa.card.CardData;
import co.com.nexos.credibanco.jpa.client.ClientData;
import co.com.nexos.credibanco.model.client.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "products")
public class ProductData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "products_SEQ")
    @SequenceGenerator(name = "products_SEQ", sequenceName = "products_seq", allocationSize = 1)
    @Column(name = "product_id", length = 6, nullable = false)
    private Integer productId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "document_client", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ClientData client;


   @Column(name = "card_id")
    private String cardId;
}
