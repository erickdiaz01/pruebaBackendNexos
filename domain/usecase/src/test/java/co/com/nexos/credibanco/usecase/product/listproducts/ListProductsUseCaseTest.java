package co.com.nexos.credibanco.usecase.product.listproducts;

import co.com.nexos.credibanco.model.client.Client;
import co.com.nexos.credibanco.model.product.Product;
import co.com.nexos.credibanco.model.product.gateways.ProductRepository;
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

class ListProductsUseCaseTest {

        @Mock
        private ProductRepository productRepository;

        @InjectMocks
        private ListProductsUseCase listProductsUseCase;

        @Test
        void listProducts() {

                Client client = Client.builder()
                                .fullName("Erick Diaz Bueno")
                                .clientId("1000375867")
                                .build();
                Product firstProduct = Product.builder()
                                .productId(100001)
                                .cardId("1000011234567890")
                                .client(client)
                                .build();
                Product secondProduct = Product.builder()
                                .productId(100002)
                                .cardId(null)
                                .client(client)
                                .build();

                List<Product> products = Arrays.asList(firstProduct, secondProduct);
                when(productRepository.listProducts()).thenReturn(Flux.fromIterable(products));

                Flux<Product> result = listProductsUseCase.listProducts();

                StepVerifier.create(result)
                                .expectNextMatches(product -> product.getCardId().length() == 16
                                                && product.getClient().getClientId().equals("1000375867"))
                                .expectNextMatches(product -> product.getCardId() == null
                                                && product.getProductId().equals(100002))
                                .expectComplete()
                                .verify();
        }
}