package com.sportshoptest.Repository;

import com.sportshoptest.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByProductId(Integer id);
    Page<Product> findAllByProductStatusOrderByProductIdAsc(Integer productStatus, Pageable pageable);
    Page<Product> findAllByCategoryTypeOrderByProductIdAsc(Integer categoryType, Pageable pageable);

    Page<Product> findAllByOrderByProductId(Pageable pageable);

    Page<Product> findByProductNameContainingIgnoreCase(String name,Pageable pageable);

    Page<Product> findAllByOrderByCreateTime(Pageable pageable);

    @Query(value = "select * from products p join product_in_order pio   on pio.product_id=p.product_id\n" +
            "group by p.product_id\n" +
            "order by sum(pio.count) desc\n", nativeQuery = true)
    Page<Product> findBySales(Pageable pageable);
}
