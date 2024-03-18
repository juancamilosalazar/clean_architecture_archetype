package co.com.banco.apirest;

import co.com.banco.apirest.product.ProductController;
import co.com.banco.model.product.Product;
import co.com.banco.usecase.ProductUseCase;
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
public class ProductControllerTest {

    private ProductUseCase productUseCase;
    private ObjectMapper objectMapper;
    private ProductController productController;

    @BeforeAll
    public void setUp() {

        productUseCase = mock(ProductUseCase.class);
        objectMapper = mock(ObjectMapper.class);
        productController = new ProductController(productUseCase,objectMapper);
    }

    @Test
    void saveTest(){
        when(productUseCase.save(Mockito.any())).thenReturn(ConstantsTest.getProduct());
        when(objectMapper.convertValue(ConstantsTest.getProductRQ(), Product.class)).thenReturn(ConstantsTest.getProduct());
        ResponseEntity<Product> customerResponseEntity= productController.save(ConstantsTest.getProductRQ());
        Assertions.assertNotNull(customerResponseEntity);
    }

    @Test
    void updateTest(){
        when(productUseCase.update(Mockito.any())).thenReturn(ConstantsTest.getProduct());
        when(objectMapper.convertValue(ConstantsTest.getProductUpdateRQ(), Product.class)).thenReturn(ConstantsTest.getProduct());
        ResponseEntity<Product> customerResponseEntity= productController.update(ConstantsTest.getProductUpdateRQ());
        Assertions.assertEquals(ConstantsTest.getProductUpdateRQ().getStatus(),
                Objects.requireNonNull(customerResponseEntity.getBody()).getStatus());
    }
}
