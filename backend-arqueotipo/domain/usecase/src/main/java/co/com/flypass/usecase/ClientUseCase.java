package co.com.flypass.usecase;

import co.com.flypass.models.Client;
import co.com.flypass.ports.inbound.ClientUseCasePort;
import co.com.flypass.ports.outbound.ClientPersistencePort;


public class ClientUseCase implements ClientUseCasePort {

    private final ClientPersistencePort clientPersistence;


    public ClientUseCase(ClientPersistencePort clientPersistence) {
        this.clientPersistence = clientPersistence;
    }
    //todo: Agregar logica de negocio necesaria para el buen funcionamiento.

    public Client save(Client client) {
        return clientPersistence.save(client);
    }

    public Client update(Client client) {
        return clientPersistence.update(client);
    }

    public void deleteClientById(Long clientId){
        clientPersistence.delete(clientId);

    }
}
