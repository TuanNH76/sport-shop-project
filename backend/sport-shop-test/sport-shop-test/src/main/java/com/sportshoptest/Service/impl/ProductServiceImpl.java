package com.sportshoptest.Service.impl;

import com.sportshoptest.Entity.Product;
import com.sportshoptest.Enums.ProductStatusEnum;
import com.sportshoptest.Enums.ResultEnum;
import com.sportshoptest.Exceptions.MyException;
import com.sportshoptest.Repository.ProductRepository;
import com.sportshoptest.Service.CategoryService;
import com.sportshoptest.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    @Override
    public Product findOne(Integer productId) {

        Product Product = productRepository.findByProductId(productId);
        return Product;
    }

    @Override
    public Page<Product> findUpAll(Pageable pageable) {
        return productRepository.findAllByProductStatusOrderByProductIdAsc(ProductStatusEnum.UP.getCode(),pageable);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAllByOrderByProductId(pageable);
    }

    @Override
    public Page<Product> findAllInCategory(Integer categoryType, Pageable pageable) {
        return productRepository.findAllByCategoryTypeOrderByProductIdAsc(categoryType, pageable);
    }
    @Override
    public Page<Product> findAllByName(String name, Pageable pageable){
        return productRepository.findByProductNameContainingIgnoreCase(name,pageable);
    }
    @Override
    @Transactional
    public void increaseStock(Integer productId, int amount) {
        Product Product = findOne(productId);
        if (Product == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        int update = Product.getProductStock() + amount;
        Product.setProductStock(update);
        productRepository.save(Product);
    }

    @Override
    @Transactional
    public void decreaseStock(Integer productId, int amount) {
        Product Product = findOne(productId);
        if (Product == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        int update = Product.getProductStock() - amount;
        if(update <= 0) throw new MyException(ResultEnum.PRODUCT_NOT_ENOUGH );

        Product.setProductStock(update);
        productRepository.save(Product);
    }

    @Override
    @Transactional
    public Product offSale(Integer productId) {
        Product Product = findOne(productId);
        if (Product == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        if (Product.getProductStatus() == ProductStatusEnum.DOWN.getCode()) {
            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        Product.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productRepository.save(Product);
    }

    @Override
    @Transactional
    public Product onSale(Integer productId) {
        Product Product = findOne(productId);
        if (Product == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);

        if (Product.getProductStatus() == ProductStatusEnum.UP.getCode()) {
            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        Product.setProductStatus(ProductStatusEnum.UP.getCode());
        return productRepository.save(Product);
    }

    @Override
    public Product update(Product Product) {

        // if null throw exception
        categoryService.findByCategoryType(Product.getCategoryType());
        if(Product.getProductStatus() > 1) {
            throw new MyException(ResultEnum.PRODUCT_STATUS_ERROR);
        }


        return productRepository.save(Product);
    }

    @Override
    public Product save(Product Product) {
        return update(Product);
    }

    @Override
    public void delete(Integer productId) {
        Product Product = findOne(productId);
        if (Product == null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
        productRepository.delete(Product);

    }
}
