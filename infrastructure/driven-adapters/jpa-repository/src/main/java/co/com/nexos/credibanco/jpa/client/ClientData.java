package co.com.nexos.credibanco.jpa.client;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "full_name", nullable = false)
    private String fullName;


}
