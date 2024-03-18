package co.com.banco.jpa.repository.transaction;

import co.com.banco.jpa.entity.TransactionEntity;
import co.com.banco.jpa.helper.AdapterOperations;
import co.com.banco.model.transaction.Transaction;
import co.com.banco.model.transaction.service.TransactionService;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepositoryAdapter extends AdapterOperations<Transaction, TransactionEntity, Long, TransactionRepository>
    implements TransactionService

{

    public TransactionRepositoryAdapter(TransactionRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Transaction.class));
    }

    @Override
    public Transaction save(Transaction transaction){
        return super.save(transaction);
    }
}
