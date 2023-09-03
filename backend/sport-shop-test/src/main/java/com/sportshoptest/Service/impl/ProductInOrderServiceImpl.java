package com.sportshoptest.Service.impl;

import com.sportshoptest.Entity.ProductInOrder;
import com.sportshoptest.Entity.User;
import com.sportshoptest.Repository.ProductInOrderRepository;
import com.sportshoptest.Service.ProductInOrderService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    @Transactional
    public void update(String itemId, Integer quantity, User user) {
        Integer itemIdInt = Integer.valueOf(itemId);
        var op = user.getCart().getProducts().stream().filter(e -> itemIdInt.equals(e.getProductId())).findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setCount(quantity);
            productInOrderRepository.save(productInOrder);
        });

    }

    @Override
    public ProductInOrder findOne(String itemId, User user) {
        Integer itemIdInt = Integer.valueOf(itemId);
        var op = user.getCart().getProducts().stream().filter(e -> itemIdInt.equals(e.getProductId())).findFirst();
        AtomicReference<ProductInOrder> res = new AtomicReference<>();
        op.ifPresent(res::set);
        return res.get();
    }
}
