package com.bassmania.msstoreproducts.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor@Builder
@ToString
public class ProductDto {
    private String productRef;
    private String category;
    private String brand;
    private String model;
    private String description;
    private Double price;
}
