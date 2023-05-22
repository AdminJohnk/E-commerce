package com.example.productmanagement.services.category;

import com.example.productmanagement.dtos.category.CategoryDTO;
import com.example.productmanagement.entities.Category;

import java.security.Principal;

public interface CategoryService {
    Category getCategory(String id);
    Category create(CategoryDTO dto, Principal principal);
    Category update(String id, CategoryDTO dto, Principal principal);
    Category delete(String id);
}