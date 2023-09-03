package com.sportshoptest.Service;

import com.sportshoptest.Entity.Cart;
import com.sportshoptest.Entity.ProductInOrder;
import com.sportshoptest.Entity.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CartService {
    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

    void delete(String itemId, User user);

    void checkout(User user);
}
