package co.com.banco.apirest.product.validationRQ;

import co.com.banco.model.client.Client;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductRQ {

    private Long id;
    @Valid
    @NotNull
    private String type;
    private String accountNumber;
    private String status;
    @Valid
    @NotNull
    private Double balance;
    @Valid
    @NotNull
    private Boolean exemptFromGMF;
    private String creationDate;
    private String modificationDate;
    @Valid
    @NotNull
    private Client client;

}