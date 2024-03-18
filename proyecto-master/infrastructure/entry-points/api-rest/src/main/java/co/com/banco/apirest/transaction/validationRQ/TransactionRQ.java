package co.com.banco.apirest.transaction.validationRQ;

import co.com.banco.model.product.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRQ {

    private Long id;

    @Valid
    @NotNull
    @Pattern(regexp = "Consignacion|Retiro|Transferencia entre cuentas", message = "El tipo de cuenta debe ser 'Consignacion','Retiro' o Transferencia entre cuentas")
    private String type;

    @Valid
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "El valor debe ser mayor que 0")
    private Double amount;
    private String date;
    @Valid
    @NotNull
    private Product sourceProduct;
    private Product destinationProduct;

}
