package co.com.banco.usecase;

import co.com.banco.model.client.service.ClientService;
import co.com.banco.model.exception.ValidationException;
import co.com.banco.model.product.Product;
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
public class ProductUseCaseTest {

    private ProductUseCase productUseCase;
    private ClientService clientService;
    private ProductService productService;

    @BeforeAll
    public void setUp() {

        clientService = mock(ClientService.class);
        productService = mock(ProductService.class);
        productUseCase = new ProductUseCase(productService,clientService);
    }

    @Test
    void saveTest() {
        when(clientService.findByIdentificationNumber(Mockito.any())).thenReturn(ConstantsTest.getClient());
        when(productService.save(Mockito.any())).thenReturn(ConstantsTest.getProduct());
        Product product = productUseCase.save(ConstantsTest.getProduct());
        Assertions.assertNotNull(product);
    }

    @Test
    void saveExceptionTest() {
        when(clientService.findByIdentificationNumber(Mockito.any())).thenReturn(null);
        Assertions.assertThrows(ValidationException.class, () ->  productUseCase.save(ConstantsTest.getProduct()));
    }

    @Test
    void updateTest() {
        when(productService.findByAccountNumber(Mockito.any())).thenReturn(ConstantsTest.getProduct());
        when(productService.save(Mockito.any())).thenReturn(ConstantsTest.getProduct());
        Product product = productUseCase.update(ConstantsTest.getProduct());
        Assertions.assertNotNull(product);
    }

    @Test
    void updateExceptionTest() {
        when(productService.findByAccountNumber(Mockito.any())).thenReturn(null);
        Assertions.assertThrows(ValidationException.class, () ->  productUseCase.update(ConstantsTest.getProduct()));
    }

    @Test
    void updateExceptionBalanceTest() {
        when(productService.findByAccountNumber(Mockito.any())).thenReturn(ConstantsTest.getProduct());
        Assertions.assertThrows(ValidationException.class, () ->  productUseCase.update(ConstantsTest.getProductBalance()));
    }
}
