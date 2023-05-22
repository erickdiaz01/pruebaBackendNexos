package co.com.nexos.credibanco.usecase.product.createproduct;

import co.com.nexos.credibanco.model.client.Client;
import co.com.nexos.credibanco.model.product.Product;
import co.com.nexos.credibanco.model.product.gateways.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class CreateProductUseCaseTest {
        @Mock
        private ProductRepository productRepository;
        @InjectMocks
        private CreateProductUseCase createProductUseCase;

        @Test
        void createProduct() {
                Client client = Client.builder()
                                .fullName("Erick Diaz Bueno")
                                .clientId("1000375867")
                                .build();
                Product product = Product.builder()
                                .productId(100001)
                                .cardId(null)
                                .client(client)
                                .build();

                when(productRepository.createProduct(product)).thenReturn(Mono.just(product));

                Mono<Product> result = createProductUseCase.createProduct(product);

                StepVerifier.create(result)
                                .expectNextMatches(productTested -> productTested.getProductId()
                                                .equals(product.getProductId())
                                                && productTested.getClient().equals(client)
                                                && productTested.getCardId() == null)

                                .expectComplete()
                                .verify();
        }
}