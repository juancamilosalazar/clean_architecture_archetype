package co.com.banco.apirest;

import co.com.banco.apirest.client.validationRQ.ClientRQ;
import co.com.banco.apirest.client.validationRQ.ClientUpdateRQ;
import co.com.banco.apirest.product.validationRQ.ProductRQ;
import co.com.banco.apirest.product.validationRQ.ProductUpdateRQ;
import co.com.banco.apirest.transaction.validationRQ.TransactionRQ;
import co.com.banco.model.client.Client;
import co.com.banco.model.product.Product;
import co.com.banco.model.transaction.Transaction;

public class ConstantsTest {

    public static Client getClient(){

        return Client.builder()
                .identificationNumber(123L)
                .dateOfBirth("1990-05-03T18:48:00")
                .emailAddress("test")
                .firstName("test")
                .identificationType("test")
                .build();
    }

    public static Transaction getTransaction(){

        return Transaction.builder()
                .sourceProduct(Product.builder()
                        .balance(12.0)
                        .build())
                .amount(12.0)
                .type("Consignacion")
                .build();
    }

    public static TransactionRQ getTransactionRQ() {

        return TransactionRQ.builder()
                .sourceProduct(Product.builder()
                        .balance(12.0)
                        .build())
                .amount(12.0)
                .type("Consignacion")

                .build();
    }
    public static Product getProduct(){

        return Product.builder()
                .type("Cuenta ahorros")
                .balance(12.0)
                .status("activa")
                .build();
    }

    public static ProductRQ getProductRQ(){

        return ProductRQ.builder()
                .type("Cuenta ahorros")
                .balance(12.0)
                .status("activa")
                .status("")
                .build();
    }

    public static ProductUpdateRQ getProductUpdateRQ(){

        return ProductUpdateRQ.builder()
                .accountNumber("1")
                .status("activa")
                .build();
    }

    public static ClientRQ getClientRQ(){

        return ClientRQ.builder()
                .identificationNumber(123L)
                .dateOfBirth("1990-05-03T18:48:00")
                .emailAddress("test")
                .firstName("test")
                .identificationType("test")
                .build();
    }

    public static ClientUpdateRQ getClientUpdateRQ(){

        return ClientUpdateRQ.builder()
                .identificationNumber(123L)
                .dateOfBirth("1990-05-03T18:48:00")
                .emailAddress("test")
                .firstName("test")
                .identificationType("test")
                .build();
    }
}
