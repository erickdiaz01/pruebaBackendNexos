package co.com.nexos.credibanco.model.client;

import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Client {
    private String clientId;
    private String fullName;

}
