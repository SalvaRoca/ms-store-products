package com.bassmania.msstoreproducts.service;

import com.bassmania.msstoreproducts.data.ProductRepository;
import com.bassmania.msstoreproducts.model.Product;
import com.bassmania.msstoreproducts.model.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ObjectMapper objectMapper;

    @Override
    public List<Product> getProducts(String productRef, String category, String brand, String model, String description, Double price) {

        if (StringUtils.hasLength(productRef) || StringUtils.hasLength(category) || StringUtils.hasLength(brand) || StringUtils.hasLength(model) || StringUtils.hasLength(description) || price != null) {
            return productRepository.search(productRef, category, brand, model, description, price);
        }

        return productRepository.getProducts();
    }

    @Override
    public Product getProductByProductRef(String productRef) {
        return productRepository.getProductByProductRef(productRef);
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        if (productDto != null
            && productDto.getProductRef() != null
            && productDto.getCategory() != null
            && productDto.getBrand() != null
            && productDto.getModel() != null
            && productDto.getDescription() != null
            && productDto.getPrice() != null
        ) {
            Product product = Product.builder()
                    .productRef(productDto.getProductRef())
                    .category(productDto.getCategory())
                    .brand(productDto.getBrand())
                    .model(productDto.getModel())
                    .description(productDto.getDescription())
                    .price(productDto.getPrice())
                    .build();

            return productRepository.saveProduct(product);
        } else {
            return null;
        }
    }

    @Override
    public Product updateProduct(Product product, ProductDto productDto) {
        if (productDto != null
            && productDto.getProductRef() != null
            && productDto.getCategory() != null
            && productDto.getBrand() != null
            && productDto.getModel() != null
            && productDto.getDescription() != null
            && productDto.getPrice() != null
        ) {
            product.setProductRef(productDto.getProductRef());
            product.setCategory(productDto.getCategory());
            product.setBrand(productDto.getBrand());
            product.setModel(productDto.getModel());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());

            return productRepository.saveProduct(product);
        } else {
            return null;
        }
    }

    @Override
    public Product updateProduct(Product existingProduct, String patchBody) {
        try {
            JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(patchBody));
            JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(existingProduct)));
            Product patched = objectMapper.treeToValue(target, Product.class);
            productRepository.saveProduct(patched);
            return patched;
        } catch (JsonProcessingException | JsonPatchException e) {
            return null;
        }
    }


    @Override
    public void deleteProduct(String productRef) {
        productRepository.deleteProduct(productRef);
    }
}
