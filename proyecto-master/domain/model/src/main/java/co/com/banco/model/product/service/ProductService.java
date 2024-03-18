package co.com.banco.model.product.service;

import co.com.banco.model.product.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> getProduct(Long idClient);

    Product findByAccountNumber(String idAccount);
}
