package com.example.productmanagement.services.category;

import com.example.productmanagement.dtos.category.CategoryDTO;
import com.example.productmanagement.entities.Category;
import com.example.productmanagement.exceptions.InvalidException;
import com.example.productmanagement.exceptions.NotFoundException;
import com.example.productmanagement.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Loại hàng hóa có id %s không tồn tại", id)));
    }

    @Override
    public Category create(CategoryDTO dto, Principal principal) {
        if (ObjectUtils.isEmpty(dto.getCode())) {
            throw new InvalidException("Mã loại hàng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên loại hàng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getDescription())) {
            throw new InvalidException("Mô tả loại hàng không được bỏ trống");
        }
        if (categoryRepository.checkCategoryCode(dto.getCode().trim().toUpperCase())) {
            throw new InvalidException(String.format("Loại hàng có mã %s đã tồn tại",
                    dto.getCode()));
        }
        Category category = new Category();
        category.setCode(dto.getCode().trim().toUpperCase());
        category.setName(dto.getName().trim());
        category.setDescription(dto.getDescription().trim());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category update(String id, CategoryDTO dto, Principal principal) {
        Category category = getCategory(id);
        if (ObjectUtils.isEmpty(dto.getCode())) {
            throw new InvalidException("Mã loại hàng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên loại hàng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getDescription())) {
            throw new InvalidException("Mô tả loại hàng không được bỏ trống");
        }
        if (!category.getCode().equalsIgnoreCase(dto.getCode().trim())
                && categoryRepository.checkCategoryCode(dto.getCode().trim().toUpperCase())) {
            throw new InvalidException(String.format("Loại hàng có mã %s đã tồn tại",
                    dto.getCode()));
        }
        category.setCode(dto.getCode().trim().toUpperCase());
        category.setName(dto.getName().trim());
        category.setDescription(dto.getDescription().trim());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category delete(String id) {
        Category category = getCategory(id);
        categoryRepository.delete(category);
        return category;
    }
}