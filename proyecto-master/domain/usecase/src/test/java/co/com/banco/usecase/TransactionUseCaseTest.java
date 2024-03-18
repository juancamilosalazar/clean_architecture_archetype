package co.com.banco.usecase;

import co.com.banco.model.exception.ValidationException;
import co.com.banco.model.product.service.ProductService;
import co.com.banco.model.transaction.Transaction;
import co.com.banco.model.transaction.service.TransactionService;
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
public class TransactionUseCaseTest {

    private TransactionUseCase transactionUseCase;
    private TransactionService transactionService;
    private ProductService productService;

    @BeforeAll
    public void setUp() {

        transactionService = mock(TransactionService.class);
        productService = mock(ProductService.class);
        transactionUseCase = new TransactionUseCase(transactionService,productService);
    }

    @Test
    void saveTest() {
        when(productService.findByAccountNumber(Mockito.any())).thenReturn(ConstantsTest.getProduct());
        when(productService.save(Mockito.any())).thenReturn(ConstantsTest.getProduct());
        when(transactionService.save(Mockito.any()
        )).thenReturn(ConstantsTest.getTransactionWITHDRAWAL());
        Transaction transaction = transactionUseCase.save(ConstantsTest.getTransactionWITHDRAWAL());
        Assertions.assertNotNull(transaction);
    }

    @Test
    void saveDepositTest() {
        when(productService.findByAccountNumber(Mockito.any())).thenReturn(ConstantsTest.getProduct());
        when(productService.save(Mockito.any())).thenReturn(ConstantsTest.getProduct());
        when(transactionService.save(Mockito.any())).thenReturn(ConstantsTest.getTransactionDeposit());
        Transaction transaction = transactionUseCase.save(ConstantsTest.getTransactionDeposit());
        Assertions.assertNotNull(transaction);
    }

    @Test
    void saveAccountTransferTest() {
        when(productService.findByAccountNumber(Mockito.any())).thenReturn(ConstantsTest.getProduct());
        when(productService.save(Mockito.any())).thenReturn(ConstantsTest.getProduct());
        when(transactionService.save(Mockito.any())).thenReturn(ConstantsTest.getTransactionAccount());
        Transaction transaction = transactionUseCase.save(ConstantsTest.getTransactionAccount());
        Assertions.assertNotNull(transaction);
    }

    @Test
    void saveProductExceptionTest(){
        when(productService.findByAccountNumber(Mockito.any())).thenReturn(null);
        Assertions.assertThrows(ValidationException.class, () -> transactionUseCase.save(ConstantsTest.getTransactionAccount()));
    }

    @Test
    void saveBalanceExceptionTest(){
        when(productService.findByAccountNumber(Mockito.any())).thenReturn(ConstantsTest.getProductNotBalance());
        Assertions.assertThrows(ValidationException.class, () -> transactionUseCase.save(ConstantsTest.getTransactionAccountBalance()));
    }

    @Test
    void saveDestinationExceptionTest(){
        when(productService.findByAccountNumber(Mockito.any())).thenReturn(ConstantsTest.getProduct());
        Assertions.assertThrows(ValidationException.class, () -> transactionUseCase.save(ConstantsTest.getTransactionAccountNotDestination()));
    }
}
