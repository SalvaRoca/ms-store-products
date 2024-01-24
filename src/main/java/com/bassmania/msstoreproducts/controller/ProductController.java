package com.bassmania.msstoreproducts.controller;

import com.bassmania.msstoreproducts.model.Product;
import com.bassmania.msstoreproducts.model.ProductDto;
import com.bassmania.msstoreproducts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String productRef,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price
    ) {
        List<Product> productList = productService.getProducts(productRef, category, brand, model, description, price);

        if (!productList.isEmpty()) {
            return ResponseEntity.ok(productList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{productRef}")
    public ResponseEntity<Product> getProductByProductRef(@PathVariable String productRef) {
        Product product = productService.getProductByProductRef(productRef);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto) {
        try {
            Product savedProduct = productService.createProduct(productDto);

            if (savedProduct != null) {
                return ResponseEntity.ok(savedProduct);
            } else {
                return ResponseEntity.badRequest().body("{\"errorMessage\": \"Some product details are missing, please check and try again\"}");
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("{\"errorMessage\": \"Product with reference " + productDto.getProductRef() + " already existing, please try with another one\"}");
        }
    }

    @PutMapping("/{productRef}")
    public ResponseEntity<?> updateProduct(@PathVariable String productRef, @RequestBody ProductDto productDto) {
        Product existingProduct = productService.getProductByProductRef(productRef);

        try {
            if (existingProduct != null) {
                Product updatedProduct = productService.updateProduct(existingProduct, productDto);

                if (updatedProduct != null) {
                    return ResponseEntity.ok(updatedProduct);
                } else {
                    return ResponseEntity.badRequest().body("{\"errorMessage\": \"Some product details are missing, please check and try again\"}");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("{\"errorMessage\": \"Product with reference " + productDto.getProductRef() + " already existing, please try with another one\"}");
        }
    }

    @PatchMapping("/{productRef}")
    public ResponseEntity<?> patchProduct(@PathVariable String productRef, @RequestBody String patchBody) {
        Product existingProduct = productService.getProductByProductRef(productRef);
        if (existingProduct != null) {
            try {
                Product updatedProduct = productService.updateProduct(existingProduct, patchBody);

                if (updatedProduct != null) {
                    return ResponseEntity.ok(updatedProduct);
                } else {
                    return ResponseEntity.badRequest().body("{\"errorMessage\": \"Some input data might be incorrect, please check and try again\"}");
                }
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.badRequest().body("{\"errorMessage\": \"Product with reference " + productRef + " already existing, please try with another one\"}");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{productRef}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productRef) {
        Product product = productService.getProductByProductRef(productRef);

        if (product != null) {
            productService.deleteProduct(productRef);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
