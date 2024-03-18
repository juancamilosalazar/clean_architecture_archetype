package co.com.banco.jpa.repository.transaction;

import co.com.banco.jpa.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long>, QueryByExampleExecutor<TransactionEntity> {
}
