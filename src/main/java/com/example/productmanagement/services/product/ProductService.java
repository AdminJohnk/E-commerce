package com.example.productmanagement.services.product;

import com.example.productmanagement.dtos.product.ProductDTO;
import com.example.productmanagement.entities.Product;
import org.springframework.data.domain.Page;

import java.security.Principal;
public interface ProductService {
    Page<Product> getAllProduct(int page, int size);
    Product getProduct(String id);
    Product create(ProductDTO dto, Principal principal);
    Product update(String id, ProductDTO dto, Principal principal);
    Product delete(String id);
}