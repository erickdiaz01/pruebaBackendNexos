package co.com.nexos.credibanco.model.product.gateways;


import co.com.nexos.credibanco.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> findProductById (String productId);
    Flux<Product> listProducts();
    Mono<Product> createProduct(Product product);
}
