package co.com.banco.jpa.repository.client;

import co.com.banco.jpa.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface ClientRepository extends CrudRepository<ClientEntity, Long>, QueryByExampleExecutor<ClientEntity> {

    List<ClientEntity> findByIdentificationNumber(Long id);

}
