package com.sportshoptest.io.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class ItemForm {
    @Min(value = 1)
    private Integer quantity;
    @NotEmpty
    private Integer productId;
}
