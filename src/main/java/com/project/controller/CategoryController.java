package com.project.controller;

import com.project.model.store.Category;
import com.project.service.mgmt.LockService;
import com.project.service.mgmt.OperationService;
import com.project.service.mgmt.TransactionService;
import com.project.service.store.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private LockService lockService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        categoryService.findAllCategories()
                .forEach(category -> {
                    if (category != null) {
                        categoryList.add(category);
                    }
                });
        if (categoryList.size() > 0)
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
