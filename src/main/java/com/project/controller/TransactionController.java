package com.project.controller;

import com.project.model.mgmt.Transaction;
import com.project.model.store.Category;
import com.project.service.mgmt.LockService;
import com.project.service.mgmt.OperationService;
import com.project.service.mgmt.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @Autowired
    private OperationService operationService;

    @Autowired
    private LockService lockService;


    @GetMapping("/all")
    public ResponseEntity<?> getTransaction() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionService.findAllTransactions()
                .forEach(category -> {
                    if (category != null) {
                        transactionList.add(category);
                    }
                });
        if (transactionList.size() > 0)
            return new ResponseEntity<>(transactionList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
