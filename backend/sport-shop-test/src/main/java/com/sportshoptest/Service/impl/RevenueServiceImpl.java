package com.sportshoptest.Service.impl;

import com.sportshoptest.Entity.Revenue;
import com.sportshoptest.Repository.OrderRepository;
import com.sportshoptest.Repository.RevenueRepository;
import com.sportshoptest.Service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    RevenueRepository revenueRepository;

    public Page<Revenue> findAll(Pageable pageable){
        return revenueRepository.findAll(pageable);
    }

}
