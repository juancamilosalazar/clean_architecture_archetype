package co.com.banco.usecase;

import co.com.banco.model.constants.Constants;
import co.com.banco.model.exception.ValidationException;
import co.com.banco.model.product.Product;
import co.com.banco.model.product.service.ProductService;
import co.com.banco.model.transaction.Transaction;
import co.com.banco.model.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RequiredArgsConstructor
public class TransactionUseCase {

    private final TransactionService transactionService;
    private final ProductService productService;

    public Transaction save(Transaction transaction) {
        if (transaction.getType().equalsIgnoreCase(Constants.WITHDRAWAL)) {

            return getWithdrawal(transaction);
        } else if (transaction.getType().equalsIgnoreCase(Constants.DEPOSIT)) {

            return getDeposit(transaction);
        } else {

            return getAccountTransfer(transaction);
        }
    }

    private Transaction getWithdrawal(Transaction transaction) {
        transaction.setDestinationProduct(null);
        Product sourceProduct = validateProduct(transaction.getSourceProduct().getAccountNumber());
        double totalBalance = sourceProduct.getBalance() - transaction.getAmount();
        if (totalBalance < 0) {
            throw new ValidationException(Constants.ERROR_500,  Constants.TR_NOT_CREATE,
                    "El monto de la cuenta origen despues de realizar la transaccion seria de: ".concat(String.valueOf(totalBalance)));
        }
        sourceProduct.setBalance(totalBalance);
        sourceProduct = productService.save(sourceProduct);
        DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.FORMAT);
        transaction.setDate(LocalDateTime.now().format(format));
        transaction.setSourceProduct(sourceProduct);

        return transactionService.save(transaction);
    }

    private Transaction getDeposit(Transaction transaction) {
        transaction.setDestinationProduct(null);
        Product sourceProduct = validateProduct(transaction.getSourceProduct().getAccountNumber());
        sourceProduct.setBalance(sourceProduct.getBalance() + transaction.getAmount());
        sourceProduct = productService.save(sourceProduct);
        DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.FORMAT);
        transaction.setDate(LocalDateTime.now().format(format));
        transaction.setSourceProduct(sourceProduct);

        return transactionService.save(transaction);
    }

    private Transaction getAccountTransfer(Transaction transaction) {
        Product sourceProduct = validateProduct(transaction.getSourceProduct().getAccountNumber());
        double totalBalance = sourceProduct.getBalance() - transaction.getAmount();
        if (totalBalance < 0) {
            throw new ValidationException(Constants.ERROR_500, Constants.TR_NOT_CREATE,
                    "El monto de la cuenta origen despues de realizar la transaccion seria de: ".concat(String.valueOf(totalBalance)));
        }
        if (Objects.isNull(transaction.getDestinationProduct())) {
            throw new ValidationException(Constants.ERROR_500,  Constants.TR_NOT_CREATE, "No exste un producto destino");
        } else {
            Product destinationProduct = validateProduct(transaction.getDestinationProduct().getAccountNumber());
            destinationProduct.setBalance(destinationProduct.getBalance() + transaction.getAmount());
            destinationProduct = productService.save(destinationProduct);
            transaction.setDestinationProduct(destinationProduct);
        }
        sourceProduct.setBalance(sourceProduct.getBalance() - transaction.getAmount());
        sourceProduct = productService.save(sourceProduct);
        DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.FORMAT);
        transaction.setDate(LocalDateTime.now().format(format));
        transaction.setSourceProduct(sourceProduct);

        return transactionService.save(transaction);
    }

    private Product validateProduct(String id) {
        Product product = productService.findByAccountNumber(id);
        if (product == null) {
            throw new ValidationException(Constants.ERROR_500,  Constants.TR_NOT_CREATE, "El producto no existe");
        }
        return product;
    }
}
