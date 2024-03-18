package co.com.banco.jpa.repository.product;

import co.com.banco.jpa.entity.ClientEntity;
import co.com.banco.jpa.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Long>, QueryByExampleExecutor<ProductEntity> {

    List<ProductEntity> getByClient(ClientEntity client);

    List<ProductEntity> findByAccountNumber(String id);

}
