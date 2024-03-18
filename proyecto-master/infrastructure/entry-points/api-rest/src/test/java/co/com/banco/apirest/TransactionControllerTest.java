package co.com.banco.apirest;

import co.com.banco.apirest.transaction.TransactionController;
import co.com.banco.model.transaction.Transaction;
import co.com.banco.usecase.TransactionUseCase;
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
public class TransactionControllerTest {

    private TransactionUseCase transactionUseCase;
    private ObjectMapper objectMapper;
    private TransactionController transactionController;

    @BeforeAll
    public void setUp() {

        transactionUseCase = mock(TransactionUseCase.class);
        objectMapper = mock(ObjectMapper.class);
        transactionController = new TransactionController(transactionUseCase,objectMapper);
    }

    @Test
    void saveTest(){
        when(transactionUseCase.save(Mockito.any())).thenReturn(ConstantsTest.getTransaction());
        when(objectMapper.convertValue(ConstantsTest.getTransactionRQ(), Transaction.class)).thenReturn(ConstantsTest.getTransaction());
        ResponseEntity<Transaction> customerResponseEntity= transactionController.save(ConstantsTest.getTransactionRQ());
        Assertions.assertEquals(ConstantsTest.getTransaction().getType(),
                Objects.requireNonNull(customerResponseEntity.getBody()).getType());
    }
}
