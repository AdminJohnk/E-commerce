package com.example.productmanagement.dtos.order;
import com.example.productmanagement.entities.embedded.Item;
import com.example.productmanagement.services.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String customerName;
    private List<Item> items;
    private Double totalAmount;
}
