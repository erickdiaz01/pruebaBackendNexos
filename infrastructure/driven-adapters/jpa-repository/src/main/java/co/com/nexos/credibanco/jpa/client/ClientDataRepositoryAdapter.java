package co.com.nexos.credibanco.jpa.client;

import co.com.nexos.credibanco.jpa.helper.AdapterOperations;
import co.com.nexos.credibanco.model.client.Client;
import co.com.nexos.credibanco.model.client.gateways.ClientRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ClientDataRepositoryAdapter extends AdapterOperations<Client/* change for domain model */, ClientData/* change for adapter model */, String, ClientDataRepository>
 implements ClientRepository
{

    public ClientDataRepositoryAdapter(ClientDataRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.mapBuilder(d, Client.ClientBuilder.class/* change for domain model */).build());
    }

    @Override
    public Mono<Client> findClientById(String clientId) {
        return null;
    }

    @Override
    public Flux<Client> listClients() {
        return null;
    }

    @Override
    public Mono<Client> createClient(Client client) {
        return null;
    }
}
