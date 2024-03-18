package co.com.banco.model.product;

import co.com.banco.model.exception.BadRequestException;

public enum ProductState {


    ACTIVA("activa"),
    INACTIVA("inactiva"),
    CANCELADA("cancelada");

    private final String name;

    ProductState(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public static String getValue(Product product) {
        if(product.getType().equalsIgnoreCase(ProductType.CUENTA_AHORROS.getName())){
            return ACTIVA.getName();
        }
        for (ProductState type : ProductState.values()) {
            if (type.getName().equalsIgnoreCase(product.getStatus())) {
                return type.getName();
            }
        }

        throw new BadRequestException("400","Error validando el request",
                "No se encontró un estado valido para la cuenta: " + product.getStatus());
    }
}