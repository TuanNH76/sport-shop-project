package com.sportshoptest.Repository;

import com.sportshoptest.Entity.OrderMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderMain,Integer> {
    OrderMain findByOrderId(Integer orderId);
    List<OrderMain> findAllByOrderByOrderStatusAscCreateTimeDesc();
    Page<OrderMain> findAllByOrderStatusOrderByCreateTimeDesc(Integer orderStatus, Pageable pageable);
    Page<OrderMain> findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(String buyerEmail, Pageable pageable);

    Page<OrderMain> findAllByOrderByOrderStatusAscCreateTimeDesc(Pageable pageable);

    Page<OrderMain> findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeDesc(String buyerPhone, Pageable pageable);

    @Query(value="SELECT SUM(o.order_amount) FROM order_main o WHERE YEAR(o.update_time) = :year AND MONTH(o.update_time) = :month AND o.order_status = 1 ", nativeQuery = true)
    Double getTotalAmountByMonthAndYear(@Param("year") Integer year, @Param("month") Integer month);
}
