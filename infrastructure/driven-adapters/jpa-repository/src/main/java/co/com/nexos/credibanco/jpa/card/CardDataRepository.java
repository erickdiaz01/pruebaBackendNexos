package co.com.nexos.credibanco.jpa.card;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface CardDataRepository extends CrudRepository<CardData/* change for adapter model */, String>, QueryByExampleExecutor<CardData/* change for adapter model */> {

}
