package com.sportshoptest.Repository;

import com.sportshoptest.Entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue,Integer> {

    public Revenue findByYearAndMonth(Integer year, Integer month);


}
