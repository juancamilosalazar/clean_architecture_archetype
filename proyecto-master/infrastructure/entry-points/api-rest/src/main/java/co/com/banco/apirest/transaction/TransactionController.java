package co.com.banco.apirest.transaction;

import co.com.banco.apirest.transaction.validationRQ.TransactionRQ;
import co.com.banco.model.transaction.Transaction;
import co.com.banco.usecase.TransactionUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionUseCase transactionUseCase;
    private final ObjectMapper objectMapper;

    @PostMapping(path = "/save")
    public ResponseEntity<Transaction> save(@Valid @RequestBody TransactionRQ transactionRQ) {

        return ResponseEntity.ok().body(transactionUseCase.save(objectMapper.convertValue(transactionRQ,Transaction.class)));
    }
}
