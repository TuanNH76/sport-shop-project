package com.sportshoptest.Controller;

import com.sportshoptest.Entity.Revenue;
import com.sportshoptest.Service.OrderService;
import com.sportshoptest.Service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class RevenueController {
    @Autowired
    OrderService orderService;
    @Autowired
    RevenueService revenueService;
    @GetMapping("/seller/revenue/cal")
    public Double calculateRevenue(@RequestParam(value = "year") Integer year,
                                   @RequestParam(value = "month") Integer month){
        return orderService.getTotalAmount(year,month);

    }

    @GetMapping("seller/revenue/show")
    public Page<Revenue> getRevenueList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "size", defaultValue = "6") Integer size){
        PageRequest request = PageRequest.of(page - 1, size);
        return revenueService.findAll(request);
    }
}
