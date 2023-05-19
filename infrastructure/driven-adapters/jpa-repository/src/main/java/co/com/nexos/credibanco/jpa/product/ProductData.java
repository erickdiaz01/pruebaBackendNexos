package co.com.nexos.credibanco.jpa.product;

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
@Table(name = "Products")
public class ProductData {
    @Id
    @Column(name = "product_id", nullable = false)
    private String productId;
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
