package co.com.banco.jpa.repository.client;

import co.com.banco.jpa.entity.ClientEntity;
import co.com.banco.jpa.helper.AdapterOperations;
import co.com.banco.model.client.Client;
import co.com.banco.model.client.service.ClientService;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepositoryAdapter extends AdapterOperations<Client, ClientEntity, Long, ClientRepository>
        implements ClientService {

    public ClientRepositoryAdapter(ClientRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Client.class));
    }

    @Override
    public Client save(Client client) {

        return toEntity(repository.save(toData(client)));
    }

    @Override
    public Client update(Client client) {
        return super.save(client);
    }

    @Override
    public Client findByIdentificationNumber(Long id) {
        List<Client> clients = new ArrayList<>();
        repository.findByIdentificationNumber(id).forEach(c -> clients.add(mapper.map(c, Client.class)));
        return clients.size() > 0 ? clients.get(0) : null;
    }

    @Override
    public void delete(Client client) {

        repository.deleteById(client.getId());
    }
}
