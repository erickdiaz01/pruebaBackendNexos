package co.com.nexos.credibanco.api;

import co.com.nexos.credibanco.model.product.Product;
import co.com.nexos.credibanco.usecase.product.createproduct.CreateProductUseCase;
import co.com.nexos.credibanco.usecase.product.listproducts.ListProductsUseCase;
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
@RequestMapping(value = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductRest {

    private final CreateProductUseCase createProductUseCase;
    private final ListProductsUseCase listProductsUseCase;

    @PostMapping
    public Mono<Product> createProduct(@RequestBody Product product) {
        return createProductUseCase.createProduct(product);
    }

    @GetMapping
    public Flux<Product> listProducts() {
        return listProductsUseCase.listProducts();
    }
}
