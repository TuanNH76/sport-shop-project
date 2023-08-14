package com.sportshoptest.Repository;

import com.sportshoptest.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByProductId(Integer id);
    Page<Product> findAllByProductStatusOrderByProductIdAsc(Integer productStatus, Pageable pageable);
    Page<Product> findAllByCategoryTypeOrderByProductIdAsc(Integer categoryType, Pageable pageable);

    Page<Product> findAllByOrderByProductId(Pageable pageable);

    Page<Product> findByProductNameContainingIgnoreCase(String name,Pageable pageable);

}
