package co.com.nexos.credibanco.jpa.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ProductDataRepository extends CrudRepository<ProductData/* change for adapter model */, String>, QueryByExampleExecutor<ProductData/* change for adapter model */> {
}
