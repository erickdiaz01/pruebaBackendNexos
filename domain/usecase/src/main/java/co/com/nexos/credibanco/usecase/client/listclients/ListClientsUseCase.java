package co.com.nexos.credibanco.usecase.client.listclients;

import co.com.nexos.credibanco.model.card.Card;
import co.com.nexos.credibanco.model.card.gateways.CardRepository;
import co.com.nexos.credibanco.model.client.Client;
import co.com.nexos.credibanco.model.client.gateways.ClientRepository;
import co.com.nexos.credibanco.model.product.Product;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ListClientsUseCase {
    private final ClientRepository clientRepository;

    public Flux<Client> listClients(){
        return clientRepository.listClients();
    }
}
