package co.com.nexos.credibanco.model.client;
import co.com.nexos.credibanco.model.product.Product;
import lombok.*;
//import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Client {
    private String clientId;
    private String fullName;

}
