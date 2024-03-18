package co.com.banco.model.client.service;

import co.com.banco.model.client.Client;
import co.com.banco.model.product.Product;

import java.util.List;

public interface ClientService {

    Client save(Client client);

    Client update(Client client);

    Client findByIdentificationNumber(Long id);

    void delete(Client client);

}
