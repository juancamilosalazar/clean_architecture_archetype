package co.com.banco.apirest.product.validationRQ;

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
public class ProductUpdateRQ {


    @Valid
    @NotNull
    private String accountNumber;
    @Valid
    @NotNull
    private String status;


}
