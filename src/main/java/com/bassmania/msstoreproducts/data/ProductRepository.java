package com.bassmania.msstoreproducts.data;

import com.bassmania.msstoreproducts.data.utils.SearchCriteria;
import com.bassmania.msstoreproducts.data.utils.SearchOperation;
import com.bassmania.msstoreproducts.data.utils.SearchStatement;
import com.bassmania.msstoreproducts.model.Product;

import org.apache.commons.lang3.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final ProductJpaRepository productJpaRepository;

    public List<Product> getProducts() {
        return productJpaRepository.findAll();
    }

    public Product getProductByProductRef(String productRef) {
        return productJpaRepository.getProductByProductRef(productRef);
    }

    public Product saveProduct(Product product) {
        return productJpaRepository.save(product);
    }

    public void deleteProduct(String productRef) {
        productJpaRepository.deleteProductByProductRef(productRef);
    }

    public List<Product> search(String productRef, String category, String brand, String model, String description, Double price) {
        SearchCriteria<Product> spec = new SearchCriteria<>();
        if (StringUtils.isNotBlank(productRef)) {
            spec.add(new SearchStatement("productRef", productRef, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(category)) {
            spec.add(new SearchStatement("category", category, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(brand)) {
            spec.add(new SearchStatement("brand", brand, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(model)) {
            spec.add(new SearchStatement("model", model, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(description)) {
            spec.add(new SearchStatement("description", description, SearchOperation.MATCH));
        }

        if (price != null) {
            spec.add(new SearchStatement("price", price, SearchOperation.EQUAL));
        }

        return productJpaRepository.findAll(spec);
    }
}
