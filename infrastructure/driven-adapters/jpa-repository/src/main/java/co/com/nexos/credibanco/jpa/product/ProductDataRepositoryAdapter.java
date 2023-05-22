package co.com.nexos.credibanco.jpa.product;

import co.com.nexos.credibanco.jpa.client.ClientData;
import co.com.nexos.credibanco.jpa.client.ClientDataRepository;
import co.com.nexos.credibanco.jpa.converters.ConverterClient;
import co.com.nexos.credibanco.jpa.converters.ConverterProduct;
import co.com.nexos.credibanco.jpa.helper.AdapterOperations;
import co.com.nexos.credibanco.model.product.Product;
import co.com.nexos.credibanco.model.product.gateways.ProductRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.ResourceAccessException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class ProductDataRepositoryAdapter extends AdapterOperations<Product/* change for domain model */, ProductData/* change for adapter model */, String, ProductDataRepository>
 implements ProductRepository
{
private final ClientDataRepository clientDataRepository;
    public ProductDataRepositoryAdapter(ProductDataRepository repository, ObjectMapper mapper, ClientDataRepository clientDataRepository) {

        super(repository, mapper, d -> mapper.mapBuilder(d, Product.ProductBuilder.class).build());
        this.clientDataRepository = clientDataRepository;
    }

    @Override
    public Mono<Product> findProductById(Long productId) {
        return repository.findById(productId.toString())
                .map(productData ->   Mono.just(ConverterProduct.convertProductDataToProduct(productData)))
                .orElseThrow(()->new ResourceAccessException("No fue posible encontrar el producto"));

    }

    @Override
    public Flux<Product> listProducts() {
        List<ProductData> products = (List<ProductData>) repository.findAll();

        return !products.isEmpty() ? Flux.fromIterable(ConverterProduct.convertProductsDataToProducts(products))
                :Flux.empty();
    }

    @Override
    public Mono<Product> createProduct(Product product) {

        if(product.getProductId()!=null){
            if(repository.findById(product.getProductId().toString()).isPresent()){
                throw new IllegalArgumentException("Producto ya creado");
            }
        }
        ClientData clientData = clientDataRepository.findById(product.getClient().getClientId()).orElseThrow(()->new ResourceAccessException("No existe el cliente en el sistema"));
        product.setClient(ConverterClient.convertClientDataToClient(clientData));
        ProductData newProduct = repository.save(ConverterProduct.convertProductToProductData(product));
        return Mono.just(ConverterProduct.convertProductDataToProduct(newProduct));
    }
}
