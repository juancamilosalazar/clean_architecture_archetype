package co.com.banco.model.product;

import co.com.banco.model.exception.BadRequestException;

public enum ProductType {

    CUENTA_AHORROS("Cuenta ahorros", "53"),
    CUENTA_CORRIENTE("Cuenta corriente", "33");

    private final String name;
    private final String extension;

    ProductType(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }

    public String getName() {
        return this.name;
    }

    public String getExtension() {
        return this.extension;
    }

    public static ProductType getValue(String valor) {
        for (ProductType type : ProductType.values()) {
            if (type.getName().equalsIgnoreCase(valor)) {
                return type;
            }
        }
        throw new BadRequestException("400", "Error validando el request",
                "No se encontró un tipo de cuenta con el valor especificado: " + valor);
    }
}
