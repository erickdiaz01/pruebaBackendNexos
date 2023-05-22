package co.com.nexos.credibanco.model.client.gateways;

import co.com.nexos.credibanco.model.client.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientRepository {
    Mono<Client> findClientById(String clientId);

    Flux<Client> listClients();

    Mono<Client> createClient(Client client);
}
