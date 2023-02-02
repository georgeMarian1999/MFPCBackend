package com.project.service.mgmt;

import com.project.model.mgmt.Transaction;
import com.project.repo.mgmt.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findAllTransactions () {
        return transactionRepository.findAll();
    }

    public void saveTransaction (Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteTransaction (Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    public void deleteTransactionById (Integer id) {
        transactionRepository.deleteById(id);
    }
    public Transaction findTransactionById (Integer id) {
        return transactionRepository.findById(id).get();
    }
}
