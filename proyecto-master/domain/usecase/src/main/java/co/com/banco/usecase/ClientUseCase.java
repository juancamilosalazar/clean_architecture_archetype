package co.com.banco.usecase;

import co.com.banco.model.client.Client;
import co.com.banco.model.client.service.ClientService;
import co.com.banco.model.constants.Constants;
import co.com.banco.model.exception.ValidationException;
import co.com.banco.model.product.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
public class ClientUseCase {

    private final ClientService clientService;

    private final ProductService productService;

    public Client save(Client client) {
        Client clientSaved = clientService.findByIdentificationNumber(client.getIdentificationNumber());
        if (clientSaved != null) {
            throw new ValidationException(Constants.ERROR_500, Constants.CLIENT_NOT_SAVE, "El cliente ya existe");
        }
        LocalDate date=LocalDateTime.now().toLocalDate();
        DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.FORMAT);
        LocalDate dateOfBirth = LocalDateTime.parse(client.getDateOfBirth(), format).toLocalDate();
        if( Period.between(dateOfBirth, date).getYears()<18)
            throw new ValidationException(Constants.ERROR_500, Constants.CLIENT_NOT_SAVE, "El cliente es menor de edad");
        client.setCreationDate(LocalDateTime.now().format(format));
        return clientService.save(client);
    }

    public Client update(Client client) {
        Client savedClient = clientService.findByIdentificationNumber(client.getIdentificationNumber());
        if (savedClient == null) {
            throw new ValidationException(Constants.ERROR_500, "Error actualizando el cliente", "El cliente no esta registrado");
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.FORMAT);
        savedClient.setModificationDate(LocalDateTime.now().format(format));
        savedClient.setDateOfBirth(client.getDateOfBirth());
        savedClient.setFirstName(client.getFirstName());
        savedClient.setLastName(client.getLastName());
        savedClient.setEmailAddress(client.getEmailAddress());
        savedClient.setIdentificationType(client.getIdentificationType());

        return clientService.save(savedClient);
    }

    public Client delete(Long id){
        Client savedClient = clientService.findByIdentificationNumber(id);
        if (savedClient == null) {
            throw new ValidationException(Constants.ERROR_500, Constants.CLIENT_NOT_DELETED, "El cliente no esta registrado");
        }

        if (!productService.getProduct(savedClient.getId()).isEmpty())
            throw new ValidationException(Constants.ERROR_500, Constants.CLIENT_NOT_DELETED, "El cliente tiene productos asociados");

        clientService.delete(savedClient);
        return savedClient;

    }
}
