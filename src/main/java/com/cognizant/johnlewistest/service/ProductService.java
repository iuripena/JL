package com.cognizant.johnlewistest.service;

import com.cognizant.johnlewistest.model.Inventory;

public interface ProductService {
    Inventory getProducts(String labelType);
}
