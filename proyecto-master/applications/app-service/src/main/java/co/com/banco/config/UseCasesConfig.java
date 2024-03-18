package co.com.banco.config;

import co.com.banco.model.client.service.ClientService;
import co.com.banco.model.product.service.ProductService;
import co.com.banco.model.transaction.service.TransactionService;
import co.com.banco.usecase.ClientUseCase;
import co.com.banco.usecase.ProductUseCase;
import co.com.banco.usecase.TransactionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

        @Bean
        public ClientUseCase clientUseCase(ClientService clientService, ProductService productService){
                return new ClientUseCase(clientService, productService);
        }

        @Bean
        public ProductUseCase productUseCase(ProductService productService, ClientService clientService){
                return new ProductUseCase(productService, clientService);
        }

        @Bean
        public TransactionUseCase transactionUseCase(TransactionService transactionService, ProductService productService){
                return new TransactionUseCase(transactionService, productService);
        }
}
