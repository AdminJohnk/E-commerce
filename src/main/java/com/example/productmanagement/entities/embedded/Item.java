package com.example.productmanagement.entities.embedded;

import com.example.productmanagement.entities.Product;
import com.example.productmanagement.services.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String productId;

    private Integer quantity;
}
