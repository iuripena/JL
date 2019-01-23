package com.cognizant.johnlewistest.controller;

import com.cognizant.johnlewistest.model.Inventory;
import com.cognizant.johnlewistest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/v1/products")
    public Inventory getProductsWithPriceReduction(@RequestParam(value = "labelType", required = false) final String labelType) {
        return productService.getProducts(labelType);
    }
}
