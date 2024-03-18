package co.com.banco.usecase;

import co.com.banco.model.client.Client;
import co.com.banco.model.client.service.ClientService;
import co.com.banco.model.constants.Constants;
import co.com.banco.model.exception.ValidationException;
import co.com.banco.model.product.Product;
import co.com.banco.model.product.ProductState;
import co.com.banco.model.product.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@RequiredArgsConstructor
public class ProductUseCase {

    private final ProductService productService;

    private final ClientService clientService;

    public Product save(Product product) {
        Client clientSaved = clientService.findByIdentificationNumber(product.getClient().getIdentificationNumber());
        if (clientSaved == null) {
            throw new ValidationException(Constants.ERROR_500, "Error creando ".concat(product.getType()),
                   Constants.CLIENT_NOT_EXIST);
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.FORMAT);
        product.setClient(clientSaved);
        product.setCreationDate(LocalDateTime.now().format(format));
        product.setAccountNumber(product.getAccountNumber() + new Random().nextInt((int) Math.pow(10, 8)));

        return productService.save(product);
    }

    public Product update(Product product) {
        Product productSaved = productService.findByAccountNumber(product.getAccountNumber());
        if (productSaved == null) {
            throw new ValidationException(Constants.ERROR_500, Constants.ERROR_UP_PRODUCT
                    .concat(String.valueOf(product.getAccountNumber())), "La cuenta no existe");
        } else if (product.getStatus().equalsIgnoreCase(ProductState.CANCELADA.getName())
                && productSaved.getBalance() > 0) {
            throw new ValidationException(Constants.ERROR_500, Constants.ERROR_UP_PRODUCT
                    .concat(String.valueOf(product.getAccountNumber())), "La cuenta tiene saldo");
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.FORMAT);
        productSaved.setModificationDate(LocalDateTime.now().format(format));
        productSaved.setStatus(product.getStatus());

        return productService.save(productSaved);

    }
}
