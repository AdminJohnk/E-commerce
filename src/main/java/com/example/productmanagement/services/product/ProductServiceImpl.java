package com.example.productmanagement.services.product;

import com.example.productmanagement.dtos.product.ProductDTO;
import com.example.productmanagement.entities.Product;
import com.example.productmanagement.exceptions.InvalidException;
import com.example.productmanagement.exceptions.NotFoundException;
import com.example.productmanagement.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> getAllProduct(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Product getProduct(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Sản phẩm có id %s không tồn tại", id)));
    }

    @Override
    public Product create(ProductDTO dto, Principal principal) {
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên sản phẩm không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPrice())) {
            throw new InvalidException("Giá sản phẩm không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getImages())) {
            throw new InvalidException("Ảnh sản phẩm không được bỏ trống");
        }

        Product product = new Product();
        product.setName(dto.getName().trim());
        product.setPrice(dto.getPrice());
        product.setImages(dto.getImages());
        productRepository.save(product);
        return product;
    }

    @Override
    public Product update(String id, ProductDTO dto, Principal principal) {
        Product product = getProduct(id);
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên sản phẩm không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPrice())) {
            throw new InvalidException("Giá sản phẩm không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getImages())) {
            throw new InvalidException("Ảnh sản phẩm không được bỏ trống");
        }
        product.setName(dto.getName().trim());
        product.setPrice(dto.getPrice());
        product.setImages(dto.getImages());
        productRepository.save(product);
        return product;
    }

    @Override
    public Product delete(String id) {
        Product product = getProduct(id);
        productRepository.delete(product);
        return product;
    }
}