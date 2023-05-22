package co.com.nexos.credibanco.jpa.client;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Size(max = 100, message = "Limite máximo de 100 caracteres")
    @Pattern(regexp = "[^0-9]*", message = "El campo no debe contener números")
    @Column(name = "full_name", nullable = false)
    private String fullName;

}
