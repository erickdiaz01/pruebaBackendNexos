package co.com.nexos.credibanco.usecase.product.createproduct;

import co.com.nexos.credibanco.model.client.Client;
import co.com.nexos.credibanco.model.client.gateways.ClientRepository;
import co.com.nexos.credibanco.model.product.Product;
import co.com.nexos.credibanco.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateProductUseCase {

    private ProductRepository productRepository;

    public Mono<Product> createProduct(Product product){
        return productRepository.createProduct(product);
    }
}
