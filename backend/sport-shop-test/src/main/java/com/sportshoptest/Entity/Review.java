package com.sportshoptest.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer review_id;
    @Max(5) @Min(0)
    private Integer rating;
    @Column(length = 255)
    private String comment;
    @NotNull
    private Integer productId;
    @NotNull
    private String userEmail;
    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

}
