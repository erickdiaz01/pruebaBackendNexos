package co.com.nexos.credibanco.usecase.client.createclient;

import co.com.nexos.credibanco.model.client.Client;
import co.com.nexos.credibanco.model.client.gateways.ClientRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateClientUseCase {
    private final ClientRepository clientRepository;

    public Mono<Client> createClient(Client client) {
        return clientRepository.createClient(client);
    }
}
