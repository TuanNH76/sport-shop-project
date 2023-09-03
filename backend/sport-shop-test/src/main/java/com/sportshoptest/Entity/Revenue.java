package com.sportshoptest.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "revenue")
public class Revenue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer revenueId;
    private Integer year;
    private Integer month;
    private Double amount;

    public Revenue(Integer year, Integer month, Double amount) {
        this.year = year;
        this.month = month;
        this.amount = amount;
    }
}
