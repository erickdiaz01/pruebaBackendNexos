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
@Table(name = "Products")
public class ProductData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, length = 6)
    private String productId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_client", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ClientData client;

    @OneToOne(mappedBy = "productData", cascade = CascadeType.ALL)
    private CardData cardData;
}
