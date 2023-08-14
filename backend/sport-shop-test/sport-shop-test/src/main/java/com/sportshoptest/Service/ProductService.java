package com.sportshoptest.Service;

import com.sportshoptest.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Product findOne(Integer productId);

    // All selling products
    Page<Product> findUpAll(Pageable pageable);
    // All products
    Page<Product> findAll(Pageable pageable);
    // All products in a category
    Page<Product> findAllInCategory(Integer categoryType, Pageable pageable);
    //Find by name
    Page<Product> findAllByName(String name,Pageable pageable);

    // increase stock
    void increaseStock(Integer productId, int amount);

    //decrease stock
    void decreaseStock(Integer productId, int amount);

    Product offSale(Integer productId);

    Product onSale(Integer productId);

    Product update(Product productInfo);
    Product save(Product productInfo);

    void delete(Integer productId);
}
