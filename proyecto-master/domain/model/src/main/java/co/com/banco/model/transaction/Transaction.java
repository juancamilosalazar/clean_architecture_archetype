package co.com.banco.model.transaction;

import co.com.banco.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Long id;
    private String type;
    private double amount;
    private String date;
    private Product sourceProduct;
    private Product destinationProduct;

}
