package com.example.productmanagement.controllers;

import com.example.productmanagement.dtos.category.CategoryDTO;
import com.example.productmanagement.entities.Category;
import com.example.productmanagement.services.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable String id) {
        return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO dto, Principal principal) {
        return new ResponseEntity<>(categoryService.create(dto, principal), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable String id, @RequestBody CategoryDTO dto, Principal principal) {
        return new ResponseEntity<>(categoryService.update(id, dto, principal), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable String id) {
        return new ResponseEntity<>(categoryService.delete(id), HttpStatus.OK);
    }
}

