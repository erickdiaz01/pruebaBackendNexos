package co.com.nexos.credibanco.api;

import co.com.nexos.credibanco.model.client.Client;
import co.com.nexos.credibanco.usecase.client.createclient.CreateClientUseCase;
import co.com.nexos.credibanco.usecase.client.listclients.ListClientsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping(value = "/api/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientRest {

    private final CreateClientUseCase createClientUseCase;
    private final ListClientsUseCase listClientsUseCase;

    @PostMapping
    public Mono<Client> createClient(@RequestBody Client client) {
        return createClientUseCase.createClient(client);
    }

    @GetMapping
    public Flux<Client> listClients() {
        return listClientsUseCase.listClients();
    }

}
