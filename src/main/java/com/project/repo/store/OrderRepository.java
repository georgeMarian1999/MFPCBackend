package com.project.repo.store;

import com.project.model.store.Order;
import com.project.model.store.OrderDetail;
import com.project.model.store.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByUser(User user);

    List<Order> findAllById(Integer id);

    List<Order> findAllByOrderDetails(OrderDetail orderDetail);
}
