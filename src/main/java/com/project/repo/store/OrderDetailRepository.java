package com.project.repo.store;


import com.project.model.store.Order;
import com.project.model.store.OrderDetail;
import com.project.model.store.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    List<OrderDetail> findAllByOrder(Order order);

    List<OrderDetail> findAllByProduct(Product product);

    void deleteAllByProductId(Integer productId);
}
