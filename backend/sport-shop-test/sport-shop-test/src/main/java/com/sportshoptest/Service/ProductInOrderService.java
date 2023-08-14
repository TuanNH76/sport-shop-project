package com.sportshoptest.Service;

import com.sportshoptest.Entity.ProductInOrder;
import com.sportshoptest.Entity.User;
import org.springframework.stereotype.Service;

@Service
public interface ProductInOrderService {
    void update(String itemId, Integer quantity, User user);
    ProductInOrder findOne(String itemId, User user);
}
