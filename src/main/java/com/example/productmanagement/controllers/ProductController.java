package com.example.productmanagement.controllers;

import com.example.productmanagement.dtos.product.ProductDTO;
import com.example.productmanagement.entities.Product;
import com.example.productmanagement.services.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO dto, Principal principal) {
        return new ResponseEntity<>(productService.create(dto, principal), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody ProductDTO dto, Principal principal) {
        return new ResponseEntity<>(productService.update(id, dto, principal), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }
}
