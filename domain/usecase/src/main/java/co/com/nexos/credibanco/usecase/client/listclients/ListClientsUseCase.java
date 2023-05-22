package co.com.nexos.credibanco.usecase.client.listclients;

import co.com.nexos.credibanco.model.client.Client;
import co.com.nexos.credibanco.model.client.gateways.ClientRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ListClientsUseCase {
    private final ClientRepository clientRepository;

    public Flux<Client> listClients() {
        return clientRepository.listClients();
    }
}
