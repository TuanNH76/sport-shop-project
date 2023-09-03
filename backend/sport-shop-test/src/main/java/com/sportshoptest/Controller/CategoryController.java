package com.sportshoptest.Controller;

import com.sportshoptest.Entity.Category;
import com.sportshoptest.Entity.Product;
import com.sportshoptest.Service.CategoryService;
import com.sportshoptest.Service.ProductService;
import com.sportshoptest.io.response.CategoryPage;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;



    @GetMapping("/category/{type}")
    public CategoryPage showOne(@PathVariable("type") Integer categoryType,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "6") Integer size) {

        Category cat = categoryService.findByCategoryType(categoryType);
        PageRequest request = PageRequest.of(page - 1, size);
        Page<Product> productInCategory = productService.findAllInCategory(categoryType, request);
        var tmp = new CategoryPage("", productInCategory);
        tmp.setCategory(cat.getCategoryName());
        return tmp;
    }
}
