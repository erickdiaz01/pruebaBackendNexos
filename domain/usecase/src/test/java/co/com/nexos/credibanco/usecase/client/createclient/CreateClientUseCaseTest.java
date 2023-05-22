package co.com.nexos.credibanco.usecase.client.createclient;

import co.com.nexos.credibanco.model.client.Client;
import co.com.nexos.credibanco.model.client.gateways.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateClientUseCaseTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private CreateClientUseCase createClientUseCase;

    @Test
    void createClient() {

        Client client = Client.builder()
                .clientId("1000375867")
                .fullName("Erick Diaz Bueno")
                .build();

        when(clientRepository.createClient(client)).thenReturn(Mono.just(client));

        Mono<Client> result = createClientUseCase.createClient(client);

        StepVerifier.create(result)
                .expectNextMatches(clientTested -> clientTested.getClientId().equals(client.getClientId())
                        && clientTested.getFullName().equals(client.getFullName()))
                .expectComplete()
                .verify();

    }
}