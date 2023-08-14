package com.sportshoptest.Service;

import com.sportshoptest.Entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> findAll();

    Category findByCategoryType(Integer categoryType);

    List<Category> findByCategoryTypeIn(List<Integer> categoryTypeList);

    Category save(Category productCategory);

}
