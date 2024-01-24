package com.bassmania.msstoreproducts.data;

import com.bassmania.msstoreproducts.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductJpaRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Product getProductByProductRef(String productRef);

    @Transactional
    void deleteProductByProductRef(String productRef);
}
