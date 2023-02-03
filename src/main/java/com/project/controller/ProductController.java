package com.project.controller;


import com.project.model.mgmt.Lock;
import com.project.model.mgmt.Transaction;
import com.project.model.mgmt.WaitFor;
import com.project.model.mgmt.enums.LockType;
import com.project.model.mgmt.enums.TransactionStatus;
import com.project.model.store.Category;
import com.project.model.store.Product;
import com.project.model.store.dto.ProductDTO;
import com.project.service.mgmt.LockService;
import com.project.service.mgmt.OperationService;
import com.project.service.mgmt.TransactionService;
import com.project.service.mgmt.WaitForService;
import com.project.service.store.CategoryService;
import com.project.service.store.OrderDetailsService;
import com.project.service.store.ProductService;
import com.project.utils.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private WaitForService waitForService;

    @Autowired
    private LockService lockService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        productService.findAllProducts()
                .forEach(product -> {
                    if (product != null) {
                        productDTOList.add(DTOUtils.productToDto(product));
                    }
                });
        if (productDTOList.size() > 0)
            return new ResponseEntity<>(productDTOList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allOnSale")
    public ResponseEntity<?> getAllProductsOnSale() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        productService.findAllProductOnSale(0)
                .forEach(product -> {
                    // List<Review> reviewsList = reviewService.findAllReviewsByProduct(Optional.ofNullable(product));
                    if (product != null) {
                        productDTOList.add(DTOUtils.productToDto(product));
                    }
                });
        if (productDTOList.size() > 0)
            return new ResponseEntity<>(productDTOList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allNew")
    public ResponseEntity<?> getAllNewProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        productService.findAllNewProducts()
                .forEach(product -> {

                    if (product != null) {
                        productDTOList.add(DTOUtils.productToDto(product));
                    }
                });
        if (productDTOList.size() > 0)
            return new ResponseEntity<>(productDTOList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/viewProduct/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable("productId") Integer productId) {
        ProductDTO productDTO = new ProductDTO();
        Optional<Product> product = productService.findProductById(productId);
        if (product.isPresent()) {
            productDTO = DTOUtils.productToDto(product.get());
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allByCategory/{categoryId}")
    public ResponseEntity<?> getAllProductsByCategory(@PathVariable("categoryId") Integer categoryId) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        Optional<Category> category = categoryService.findCategoryById(categoryId);
        category.ifPresent(value ->
                productService.findProductsByCategory(value)
                        .forEach(product -> {
                            if (product != null) {
                            productDTOList.add(DTOUtils.productToDto(product));
                            }
                        }));
        if (productDTOList.size() > 0)
            return new ResponseEntity<>(productDTOList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<?> addProduct(@PathVariable("userId") Integer userId, @RequestBody ProductDTO productDTO) {
        Product product = new Product(productDTO.getName(), productDTO.getDescription(),productDTO.getImage(), productDTO.getNewProduct(), productDTO.getPrice(),productDTO.getStock(), productDTO.getAddedDate(), productDTO.getCategory());

        productService.saveProduct(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PutMapping("/test-deadlock/{productId}/{productId2}")
    public ResponseEntity<?> testDeadLock(@PathVariable("productId") Integer productId, @PathVariable("productId2") Integer productId2) {
        Transaction transaction = new Transaction();
        transactionService.saveTransaction(transaction);
        Lock lock = new Lock(LockType.WRITE, productId,"product", transaction);
        while (lockService.isLocked("product", productId, transaction)) {
            try {
                Lock otherLock = lockService.findLockByTableAndObjectId("product", productId2);
                if(otherLock != null) {
                    WaitFor waitFor2 = new WaitFor(lock.getLockType(), lock.getTableName(), lock.getObjectId(), otherLock.getTransaction().getId(), transaction.getId());
                    if (waitForService.findByTransactionHasLockId(transaction.getId()) == null) {
                        waitForService.saveWaitFor(waitFor2);
                    }
                    WaitFor waitForCycle = waitForService.findByTransactionWaitsForLockId(otherLock.getTransaction().getId());
                    if(waitForCycle != null && Objects.equals(waitForCycle.getLockTable(), waitFor2.getLockTable()) && Objects.equals(waitForCycle.getTransactionHasLockId(), transaction.getId()) && Objects.equals(waitForCycle.getLockObjectId(), productId)) {
                        waitForService.deleteWaitFor(waitFor2);
                        lockService.deleteAllByTransaction(transaction);
                        System.out.println("Deadlock detected");
                        return new ResponseEntity<>(HttpStatus.CONFLICT);
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        lockService.saveLock(lock);
        Product product1 = productService.findProductById(productId).get();
        product1.setStock(product1.getStock() + 1);


        Lock lock2 = new Lock(LockType.WRITE, productId2,"product", transaction);
        while (lockService.isLocked("product", productId2, transaction)) {
            try {
                Lock otherLock = lockService.findLockByTableAndObjectId("product", productId2);
                if(otherLock != null) {
                    WaitFor waitFor = new WaitFor(lock2.getLockType(), lock2.getTableName(), lock2.getObjectId(), otherLock.getTransaction().getId(), transaction.getId());
                    if (waitForService.findByTransactionHasLockId(transaction.getId()) == null) {
                        waitForService.saveWaitFor(waitFor);
                    }
                    WaitFor waitForCycle = waitForService.findByTransactionWaitsForLockId(otherLock.getTransaction().getId());
                    if(waitForCycle != null && Objects.equals(waitForCycle.getLockTable(), waitFor.getLockTable()) && Objects.equals(waitForCycle.getTransactionHasLockId(), transaction.getId()) && Objects.equals(waitForCycle.getLockObjectId(), productId)) {
                        waitForService.deleteWaitFor(waitFor);
                        lockService.deleteAllByTransaction(transaction);
                        System.out.println("Deadlock detected");
                        return new ResponseEntity<>(HttpStatus.CONFLICT);
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        lockService.saveLock(lock2);
        Product product2 = productService.findProductById(productId).get();
        product2.setStock(product2.getStock() + 1);


        lockService.deleteAllByTransaction(transaction);
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDTO productDTO) {
        Transaction transaction = new Transaction();
        transactionService.saveTransaction(transaction);
        Lock lock = new Lock(LockType.WRITE, productId,"product", transaction);
        while (lockService.isLocked("product", productId, transaction)) {
            try {
                Lock otherLock = lockService.findLockByTableAndObjectId("product", productId);
                if(otherLock != null) {
                    WaitFor waitFor = new WaitFor(otherLock.getLockType(), otherLock.getTableName(), otherLock.getObjectId(), otherLock.getTransaction().getId(), transaction.getId());
                    waitForService.saveWaitFor(waitFor);
                    WaitFor waitForCycle = waitForService.findByTransactionWaitsForLockId(otherLock.getTransaction().getId());
                    if(waitForCycle != null && Objects.equals(waitForCycle.getLockTable(), "product") && Objects.equals(waitForCycle.getTransactionHasLockId(), transaction.getId()) && Objects.equals(waitForCycle.getLockObjectId(), productId)) {
                        waitForService.deleteWaitFor(waitFor);
                        System.out.println("Deadlock detected");
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        lockService.saveLock(lock);

        Product product = productService.findProductById(productId).get();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        product.setNewProduct(productDTO.getNewProduct());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setAddedDate(productDTO.getAddedDate());
        product.setCategory(productDTO.getCategory());
        productService.saveProduct(product);

        transaction.setStatus(TransactionStatus.COMMIT);
        transactionService.saveTransaction(transaction);

        lockService.deleteAllByTransaction(transaction);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") Integer productId) {
        Transaction transaction = new Transaction();
        transactionService.saveTransaction(transaction);
        Lock lock = new Lock(LockType.WRITE, productId,"product", transaction);
        while (lockService.isLocked("product", productId, transaction)) {
            try {
                Lock otherLock = lockService.findLockByTableAndObjectId("product", productId);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        lockService.saveLock(lock);

        productService.deleteById(productId);

        transaction.setStatus(TransactionStatus.COMMIT);
        transactionService.saveTransaction(transaction);

        lockService.deleteAllByTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
