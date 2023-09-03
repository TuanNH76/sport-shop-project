package com.sportshoptest.Entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "products")
@DynamicUpdate
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @NotNull
    private String productName;
    @NotNull
    private BigDecimal productPrice;
    @NotNull
    @Min(0)
    private Integer productStock;
    private String productDescription;
    private String productImage;
    /** 0: on-sale 1: off-sale */
    @ColumnDefault("0")
    private Integer productStatus;
    @ColumnDefault("0")
    private Integer categoryType;
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
    public Product() {
    }

}
