package com.sportshoptest.Service.impl;

import com.sportshoptest.Entity.Cart;
import com.sportshoptest.Entity.OrderMain;
import com.sportshoptest.Entity.ProductInOrder;
import com.sportshoptest.Entity.User;
import com.sportshoptest.Enums.ResultEnum;
import com.sportshoptest.Exceptions.MyException;
import com.sportshoptest.Repository.CartRepository;
import com.sportshoptest.Repository.OrderRepository;
import com.sportshoptest.Repository.ProductInOrderRepository;
import com.sportshoptest.Repository.UserRepository;
import com.sportshoptest.Service.CartService;
import com.sportshoptest.Service.ProductService;
import com.sportshoptest.Service.UserService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductInOrderRepository productInOrderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserService userService;

    @Override
    public Cart getCart(User user) {
        return user.getCart();
    }

    @Override
    @Transactional
    public void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user) {
        Cart finalCart = user.getCart();
        productInOrders.forEach(productInOrder -> {
            Set<ProductInOrder> set = finalCart.getProducts();
            Optional<ProductInOrder> old = set.stream().filter(e -> e.getProductId().equals(productInOrder.getProductId())).findFirst();
            ProductInOrder prod;
            if (old.isPresent()) {
                prod = old.get();
                prod.setCount(productInOrder.getCount() + prod.getCount());
            } else {
                prod = productInOrder;
                prod.setCart(finalCart);
                finalCart.getProducts().add(prod);
            }
            productInOrderRepository.save(prod);
        });
        cartRepository.save(finalCart);

    }

    @Override
    @Transactional
    public void delete(String itemId, User user) {
        if(itemId.equals("") || user == null) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }
        Integer itemIdInt=Integer.valueOf(itemId);
        var op = user.getCart().getProducts().stream().filter(e -> itemIdInt.equals(e.getProductId())).findFirst();
//        op.ifPresent(productInOrder -> {
//            productInOrder.setCart(null);
//            productInOrderRepository.deleteById(productInOrder.getId());
//        });
        if (op.isPresent()) {
            ProductInOrder productInOrder = op.get();
            productInOrder.setCart(null);
            productInOrderRepository.deleteById(productInOrder.getId());
        } else {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }
    }

    @Override
    @Transactional
    public void checkout(User user) {
        // Creat an order
        OrderMain order = new OrderMain(user);
        orderRepository.save(order);

        // clear cart's foreign key & set order's foreign key& decrease stock
        user.getCart().getProducts().forEach(productInOrder -> {
            productInOrder.setCart(null);
            productInOrder.setOrderMain(order);
            productService.decreaseStock(productInOrder.getProductId(), productInOrder.getCount());
            productInOrderRepository.save(productInOrder);
        });

    }
}
