package co.com.nexos.credibanco.jpa.client;


import co.com.nexos.credibanco.model.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "Clients")
public class ClientData {
    @Id
    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Size(max = 50, message = "Limite máximo de 50 caracteres")
    @Pattern(regexp = "[^0-9]*", message = "El campo no debe contener números")
    @Column(name = "full_name", nullable = false)
    private String fullName;



}
