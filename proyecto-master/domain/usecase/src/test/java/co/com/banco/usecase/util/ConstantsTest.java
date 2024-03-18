package co.com.banco.usecase.util;

import co.com.banco.model.client.Client;
import co.com.banco.model.product.Product;
import co.com.banco.model.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

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


    public static List<Product> getProductList(){
        List<Product> list= new ArrayList<>();
        Product product= Product.builder()
                .id(1L)
                .build();
        list.add(product);

        return list;
    }

    public static Product getProduct(){

        return Product.builder()
                .id(1L)
                .accountNumber("123123")
                .balance(1232.0)
                .type("test")
                .status("test")
                .balance(12.0)
                .client(Client.builder()
                        .identificationNumber(1L)
                        .build())
                .build();
    }

    public static Product getProductNotBalance(){

        return Product.builder()
                .id(1L)
                .accountNumber("123123")
                .balance(0.0)
                .type("test")
                .status("test")
                .balance(12.0)
                .client(Client.builder()
                        .identificationNumber(1L)
                        .build())
                .build();
    }

    public static Product getProductBalance(){

        return Product.builder()
                .id(1L)
                .accountNumber("123123")
                .balance(1232.0)
                .type("test")
                .status("cancelada")
                .balance(12.0)
                .client(Client.builder()
                        .identificationNumber(1L)
                        .build())
                .build();
    }

    public static Transaction getTransactionWITHDRAWAL(){

        return Transaction.builder()
                .type("Retiro")
                .amount(1)
                .sourceProduct(Product.builder()
                        .accountNumber("123123")
                        .build())
                .build();
    }

    public static Transaction getTransactionDeposit(){

        return Transaction.builder()
                .type("Consignacion")
                .amount(1)
                .sourceProduct(Product.builder()
                        .accountNumber("123123")
                        .build())
                .build();
    }

    public static Transaction getTransactionAccount(){

        return Transaction.builder()
                .type("Transferencia entre cuentas")
                .amount(1)
                .sourceProduct(Product.builder()
                        .accountNumber("123123")
                        .build())
                .destinationProduct(Product.builder()
                        .accountNumber("123123")
                        .build())
                .build();
    }

    public static Transaction getTransactionAccountBalance(){

        return Transaction.builder()
                .type("Transferencia entre cuentas")
                .amount(13)
                .sourceProduct(Product.builder()
                        .accountNumber("123123")
                        .build())
                .destinationProduct(Product.builder()
                        .accountNumber("123123")
                        .build())
                .build();
    }

    public static Transaction getTransactionAccountNotDestination(){

        return Transaction.builder()
                .type("Transferencia entre cuentas")
                .amount(10)
                .sourceProduct(Product.builder()
                        .accountNumber("123123")
                        .build())
                .build();
    }

}
