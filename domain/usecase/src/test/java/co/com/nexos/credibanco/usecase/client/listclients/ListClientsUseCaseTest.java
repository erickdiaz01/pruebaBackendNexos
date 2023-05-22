package co.com.nexos.credibanco.usecase.client.listclients;

import co.com.nexos.credibanco.model.client.Client;
import co.com.nexos.credibanco.model.client.gateways.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListClientsUseCaseTest {

        @Mock
        private ClientRepository clientRepository;

        @InjectMocks
        private ListClientsUseCase listClientsUseCase;

        @Test
        void listClients() {
                Client firstClient = Client.builder()
                                .clientId("1000375867")
                                .fullName("Erick Diaz Bueno")
                                .build();
                Client secondClient = Client.builder()
                                .clientId("123456789")
                                .fullName("Santiago Ramirez")
                                .build();
                List<Client> clients = Arrays.asList(firstClient, secondClient);
                when(clientRepository.listClients()).thenReturn(Flux.fromIterable(clients));

                Flux<Client> result = listClientsUseCase.listClients();

                StepVerifier.create(result)
                                .expectNextSequence(clients)
                                .expectComplete()
                                .verify();
        }
}