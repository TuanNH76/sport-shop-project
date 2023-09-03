package com.sportshoptest.Controller;

import com.sportshoptest.Entity.Product;
import com.sportshoptest.Service.CategoryService;
import com.sportshoptest.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    /**
     * Show All Categories
     */

    @GetMapping("/product")
    public Page<Product> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "6") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return productService.findAll(request);
    }

    @GetMapping("/product/{productId}")
    public Product showOne(@PathVariable("productId") Integer productId) {

        Product Product = productService.findOne(productId);

        return Product;
    }

    @GetMapping("product/best-seller")
    public Page<Product> findByTopSale(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "6") Integer size){
        PageRequest request = PageRequest.of(page-1,size);
        return productService.findByTopSale(request);
    }

    @PostMapping("/seller/product/new")
    public ResponseEntity create(@Valid @RequestBody Product product,
                                 BindingResult bindingResult) {
        Product productIdExists = productService.findOne(product.getProductId());
        if (productIdExists != null) {
            bindingResult
                    .rejectValue("productId", "error.product",
                            "There is already a product with the code provided");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/seller/product/{id}/edit")
    public ResponseEntity edit(@PathVariable("id") String productId,
                               @Valid @RequestBody Product product,
                               BindingResult bindingResult) {
        Integer id = Integer.parseInt(productId);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!id.equals(product.getProductId())) {
            return ResponseEntity.badRequest().body("Id Not Matched");
        }

        return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("/seller/product/{id}/delete")
    public ResponseEntity delete(@PathVariable("id") Integer productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/product/search/{name}")
    public Page<Product> findAllByName(@PathVariable("name")String name,
                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "3") Integer size){
        PageRequest request = PageRequest.of(page - 1, size);
        return productService.findAllByName(name,request);

    }



}
