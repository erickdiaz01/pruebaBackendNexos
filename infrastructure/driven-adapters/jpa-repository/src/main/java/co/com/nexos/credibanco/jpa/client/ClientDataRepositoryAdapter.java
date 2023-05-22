package co.com.nexos.credibanco.jpa.client;

import co.com.nexos.credibanco.jpa.card.CardData;
import co.com.nexos.credibanco.jpa.converters.ConverterClient;
import co.com.nexos.credibanco.jpa.helper.AdapterOperations;
import co.com.nexos.credibanco.model.client.Client;
import co.com.nexos.credibanco.model.client.gateways.ClientRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.ResourceAccessException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static co.com.nexos.credibanco.jpa.converters.ConverterCard.convertCardsDataToCards;

@Repository
public class ClientDataRepositoryAdapter extends AdapterOperations<Client/* change for domain model */, ClientData/* change for adapter model */, String, ClientDataRepository>
 implements ClientRepository
{

    public ClientDataRepositoryAdapter(ClientDataRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.mapBuilder(d, Client.ClientBuilder.class).build());
    }

    @Override
    public Mono<Client> findClientById(String clientId) {
        return repository.findById(clientId)
                .map(clientData -> Mono.just(ConverterClient.convertClientDataToClient(clientData)))
                .orElseThrow(()->new ResourceAccessException("No fue posible encontrar el cliente"));

    }

    @Override
    public Flux<Client> listClients() {
        List<ClientData> clients = (List<ClientData>) repository.findAll();

        return !clients.isEmpty() ? Flux.fromIterable(ConverterClient.convertClientsDataToClients(clients))
                :Flux.empty();
    }

    @Override
    public Mono<Client> createClient(Client client) {
        if(repository.findById(client.getClientId()).isPresent()){
            throw new IllegalArgumentException("Cliente ya creado");
        }
        ClientData newClient = repository.save(ConverterClient.convertClientToClientData(client));
        return Mono.just(ConverterClient.convertClientDataToClient(newClient));
    }
}
