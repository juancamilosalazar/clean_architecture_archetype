package co.com.banco.apirest.product.validationRQ;

import co.com.banco.model.exception.BadRequestException;
import co.com.banco.model.product.ProductState;
import co.com.banco.model.product.ProductType;

public class ProductValidator {

    public static void validateStatus(String status) {
        if (!ProductState.ACTIVA.getName().equalsIgnoreCase(status)
                && !ProductState.INACTIVA.getName().equalsIgnoreCase(status)
                && !ProductState.CANCELADA.getName().equalsIgnoreCase(status)) {

            throw new BadRequestException("400", "Error validando el request",
                    "No se encontró un estado valido para la cuenta: ".concat(status));
        }
    }

    public static void validateBalance(Double balance, String type) {
        if (type.equalsIgnoreCase(ProductType.CUENTA_AHORROS.getName()) && balance < 0) {
            throw new BadRequestException("400", "Error creando el producto",
                    "El saldo no corresponde al tipo de cuenta: ".concat(type));
        }
    }
}
