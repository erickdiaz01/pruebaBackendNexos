package co.com.nexos.credibanco.jpa.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface TransactionDataRepository extends CrudRepository<TransactionData/* change for adapter model */, String>, QueryByExampleExecutor<TransactionData/* change for adapter model */> {
}
