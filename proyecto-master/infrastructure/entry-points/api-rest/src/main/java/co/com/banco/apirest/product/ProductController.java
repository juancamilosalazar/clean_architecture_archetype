package co.com.banco.apirest.product;

import co.com.banco.apirest.product.validationRQ.ProductRQ;
import co.com.banco.apirest.product.validationRQ.ProductUpdateRQ;
import co.com.banco.apirest.product.validationRQ.ProductValidator;
import co.com.banco.model.product.Product;
import co.com.banco.model.product.ProductState;
import co.com.banco.model.product.ProductType;
import co.com.banco.usecase.ProductUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;
    private final ObjectMapper objectMapper;

    @PostMapping(path = "/save")
    public ResponseEntity<Product> save(@Valid @RequestBody ProductRQ productRQ) {

        Product product=objectMapper.convertValue(productRQ,Product.class);
        product.setAccountNumber(ProductType.getValue(product.getType()).getExtension());
        product.setStatus(ProductState.getValue(product));
        ProductValidator.validateBalance(product.getBalance(),product.getType());
        return ResponseEntity.ok().body(productUseCase.save(product));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Product> update(@RequestBody ProductUpdateRQ productUpdateRQ) {
        Product product= objectMapper.convertValue(productUpdateRQ,Product.class);
        ProductValidator.validateStatus(product.getStatus());
        return ResponseEntity.ok().body(productUseCase.update(product));
    }

}
