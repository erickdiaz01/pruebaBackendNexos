package co.com.nexos.credibanco.usecase.product.listproducts;

import co.com.nexos.credibanco.model.client.Client;
import co.com.nexos.credibanco.model.client.gateways.ClientRepository;
import co.com.nexos.credibanco.model.product.Product;
import co.com.nexos.credibanco.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ListProductsUseCase {
    private ProductRepository productRepository;

    public Flux<Product> listProducts(){
        return productRepository.listProducts();
    }
}