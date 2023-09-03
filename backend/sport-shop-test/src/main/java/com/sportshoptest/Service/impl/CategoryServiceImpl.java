package com.sportshoptest.Service.impl;

import com.sportshoptest.Entity.Category;
import com.sportshoptest.Enums.ResultEnum;
import com.sportshoptest.Exceptions.MyException;
import com.sportshoptest.Repository.CategoryRepository;
import com.sportshoptest.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository productCategoryRepository;



    @Override
    public List<Category> findAll() {
        List<Category> res = productCategoryRepository.findAllByOrderByCategoryType();
        //  res.sort(Comparator.comparing(ProductCategory::getCategoryType));
        return res;
    }

    @Override
    public Category findByCategoryType(Integer categoryType) {
        Category res = productCategoryRepository.findByCategoryType(categoryType);
        if(res == null) throw new MyException(ResultEnum.CATEGORY_NOT_FOUND);
        return res;
    }

    @Override
    public List<Category> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<Category> res = productCategoryRepository.findByCategoryTypeInOrderByCategoryTypeAsc(categoryTypeList);
        //res.sort(Comparator.comparing(ProductCategory::getCategoryType));
        return res;
    }

    @Override
    @Transactional
    public Category save(Category productCategory) {
        return productCategoryRepository.save(productCategory);
    }

}
