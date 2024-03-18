package co.com.banco.jpa.repository.product;

import co.com.banco.jpa.entity.ClientEntity;
import co.com.banco.jpa.entity.ProductEntity;
import co.com.banco.jpa.helper.AdapterOperations;
import co.com.banco.model.product.Product;
import co.com.banco.model.product.service.ProductService;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryAdapter extends AdapterOperations<Product, ProductEntity, Long, ProductRepository>
        implements ProductService {

    public ProductRepositoryAdapter(ProductRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Product.class));
    }

    @Override
    public Product save(Product product) {
        return toEntity(repository.save(toData(product)));
    }

    @Override
    public List<Product> getProduct(Long idClient) {

        return repository.getByClient(ClientEntity.builder()
                .id(idClient)
                .build()).stream().map(productEntity ->
                mapper.map(productEntity, Product.class)).collect(Collectors.toList());

    }

    @Override
    public  Product findByAccountNumber(String idAccount) {
        List<Product> list = new ArrayList<>();
        repository.findByAccountNumber(idAccount).forEach(c -> list.add(mapper.map(c, Product.class)));
        return list.size() > 0 ? list.get(0) : null;

    }
}
