package com.sportshoptest.Service;

import com.sportshoptest.Entity.OrderMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Page<OrderMain> findAll(Pageable pageable);
    List<OrderMain> findAll();

    Page<OrderMain> findByStatus(Integer status, Pageable pageable);

    Page<OrderMain> findByBuyerEmail(String email, Pageable pageable);

    Page<OrderMain> findByBuyerPhone(String phone, Pageable pageable);

    OrderMain findOne(Integer orderId);


    OrderMain finish(Integer orderId);

    OrderMain cancel(Integer orderId);

    Double getTotalAmount(Integer year, Integer month);

}
