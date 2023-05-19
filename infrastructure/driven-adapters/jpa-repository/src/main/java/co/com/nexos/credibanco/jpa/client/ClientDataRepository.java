package co.com.nexos.credibanco.jpa.client;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ClientDataRepository extends CrudRepository<ClientData/* change for adapter model */, String>, QueryByExampleExecutor<ClientData/* change for adapter model */> {
}
