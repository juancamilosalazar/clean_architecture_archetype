package co.com.banco.apirest;

import co.com.banco.apirest.client.ClientController;
import co.com.banco.model.client.Client;
import co.com.banco.usecase.ClientUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClientControllerTest {

    private ClientUseCase clientUseCase;
    private ObjectMapper objectMapper;
    private ClientController clientController;

    @BeforeAll
    public void setUp() {

        clientUseCase = mock(ClientUseCase.class);
        objectMapper = mock(ObjectMapper.class);
        clientController = new ClientController(clientUseCase,objectMapper);
    }

    @Test
    void addTest(){
        when(clientUseCase.save(Mockito.any())).thenReturn(ConstantsTest.getClient());
        when(objectMapper.convertValue(ConstantsTest.getClientRQ(), Client.class)).thenReturn(ConstantsTest.getClient());
        ResponseEntity<Client> customerResponseEntity= clientController.add(ConstantsTest.getClientRQ());
        Assertions.assertEquals(ConstantsTest.getClient().getIdentificationNumber(),
                Objects.requireNonNull(customerResponseEntity.getBody()).getIdentificationNumber());
    }

    @Test
    void updateTest(){
        when(clientUseCase.update(Mockito.any())).thenReturn(ConstantsTest.getClient());
        when(objectMapper.convertValue(ConstantsTest.getClientUpdateRQ(), Client.class)).thenReturn(ConstantsTest.getClient());
        ResponseEntity<Client> customerResponseEntity= clientController.update(ConstantsTest.getClientUpdateRQ());
        Assertions.assertEquals(ConstantsTest.getClient().getIdentificationNumber(),
                Objects.requireNonNull(customerResponseEntity.getBody()).getIdentificationNumber());
    }

    @Test
    void deleteTest(){
        when(clientUseCase.delete(Mockito.any())).thenReturn(ConstantsTest.getClient());
        ResponseEntity<String> customerResponseEntity= clientController.delete("1");
        Assertions.assertNotNull(customerResponseEntity);
    }
}
