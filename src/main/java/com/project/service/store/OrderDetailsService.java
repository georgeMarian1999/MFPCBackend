package com.project.service.store;

import com.project.repo.store.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;


    public void deleteOrderDetailByProductId(Integer id) {
        orderDetailRepository.deleteAllByProductId(id);
    }
}
