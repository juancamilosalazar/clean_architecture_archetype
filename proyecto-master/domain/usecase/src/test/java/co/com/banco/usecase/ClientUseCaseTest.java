package co.com.banco.usecase;

import co.com.banco.model.client.Client;
import co.com.banco.model.client.service.ClientService;
import co.com.banco.model.exception.ValidationException;
import co.com.banco.model.product.service.ProductService;
import co.com.banco.usecase.util.ConstantsTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClientUseCaseTest {

    private ClientUseCase clientUseCase;
    private ClientService clientService;
    private ProductService productService;

    @BeforeAll
    public void setUp() {

        clientService = mock(ClientService.class);
        productService = mock(ProductService.class);
        clientUseCase = new ClientUseCase(clientService, productService);
    }

    @Test
    void saveTest() {
        when(clientService.findByIdentificationNumber(Mockito.any())).thenReturn(null);
        when(clientService.save(Mockito.any())).thenReturn(ConstantsTest.getClient());
        Client client = clientUseCase.save(ConstantsTest.getClient());
        Assertions.assertNotNull(client);
    }

    @Test
    void saveExceptionTest() {
        when(clientService.findByIdentificationNumber(Mockito.any())).thenReturn(ConstantsTest.getClient());
        Assertions.assertThrows(ValidationException.class, () -> clientUseCase.save(ConstantsTest.getClient()));
    }

    @Test
    void updateTest() {
        when(clientService.findByIdentificationNumber(Mockito.any())).thenReturn(ConstantsTest.getClient());
        when(clientService.save(Mockito.any())).thenReturn(ConstantsTest.getClient());
        Client client = clientUseCase.update(ConstantsTest.getClient());
        Assertions.assertNotNull(client);
    }


    @Test
    void updateExceptionTest() {
        when(clientService.findByIdentificationNumber(Mockito.any())).thenReturn(null);
        Assertions.assertThrows(ValidationException.class, () -> clientUseCase.update(ConstantsTest.getClient()));
    }

    @Test
    void deleteTest() {
        clientService = mock(ClientService.class);
        productService = mock(ProductService.class);
        clientUseCase = new ClientUseCase(clientService, productService);
        when(clientService.findByIdentificationNumber(Mockito.any())).thenReturn(ConstantsTest.getClient());
        when(productService.findByAccountNumber(Mockito.any())).thenReturn(null);
        clientService.delete(ConstantsTest.getClient());
        Client client = clientUseCase.delete(ConstantsTest.getClient().getIdentificationNumber());
        Assertions.assertNotNull(client);
    }

    @Test
    void deleteExceptionTest() {
        when(clientService.findByIdentificationNumber(Mockito.any())).thenReturn(null);
        Assertions.assertThrows(ValidationException.class, () -> clientUseCase.delete(Mockito.any()));
    }

    @Test
    void deleteExceptionProductTest() {
        when(clientService.findByIdentificationNumber(Mockito.any())).thenReturn(ConstantsTest.getClient());
        when(productService.getProduct(Mockito.any())).thenReturn(ConstantsTest.getProductList());
        Assertions.assertThrows(ValidationException.class, () -> clientUseCase.delete(Mockito.any()));
    }

}
