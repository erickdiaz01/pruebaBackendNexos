package co.com.nexos.credibanco.model.product;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {
    private String productId;
    private String documentClient;
    private String titularName;
    private LocalDate expirationDate;
    private Boolean isActivated;
    private Boolean isBlocked;
    private Integer balance;
}
