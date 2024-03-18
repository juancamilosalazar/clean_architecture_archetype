package co.com.banco.model.product;

import co.com.banco.model.client.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private String type;
    private String accountNumber;
    private String status;
    private Double balance;
    private Boolean exemptFromGMF;
    private String creationDate;
    private String modificationDate;
    private Client client;

}