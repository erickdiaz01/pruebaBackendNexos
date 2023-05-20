package co.com.nexos.credibanco.jpa.product;

import co.com.nexos.credibanco.jpa.helper.AdapterOperations;
import co.com.nexos.credibanco.model.product.Product;
import co.com.nexos.credibanco.model.product.gateways.ProductRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProductDataRepositoryAdapter extends AdapterOperations<Product/* change for domain model */, ProductData/* change for adapter model */, String, ProductDataRepository>
 implements ProductRepository
{

    public ProductDataRepositoryAdapter(ProductDataRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.map(d, Product.class/* change for domain model */));
    }

    @Override
    public Mono<Product> findProductById(String productId) {
        return null;
    }

    @Override
    public Flux<Product> listProducts() {
        return null;
    }

    @Override
    public Mono<Product> createProduct(Product product) {
        return null;
    }
}
