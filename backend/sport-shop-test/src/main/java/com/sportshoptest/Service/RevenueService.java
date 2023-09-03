package com.sportshoptest.Service;

import com.sportshoptest.Entity.Revenue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RevenueService {
    public Page<Revenue> findAll(Pageable pageable);
}
