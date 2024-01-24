package com.bassmania.msstoreproducts.service;

import com.bassmania.msstoreproducts.model.Product;
import com.bassmania.msstoreproducts.model.ProductDto;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(String productRef, String category, String brand, String model, String description, Double price);

    Product getProductByProductRef(String productRef);

    Product createProduct(ProductDto request);

    Product updateProduct(Product existingProduct, ProductDto productDto);

    Product updateProduct(Product existingProduct, String patchBody);

    void deleteProduct(String productRef);
}
