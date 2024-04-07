package com.example.springboot.services;

import com.example.springboot.dtos.ProductRequestDTO;
import com.example.springboot.models.ProductModel;
import com.example.springboot.models.exceptions.ProductNotFoundException;
import com.example.springboot.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductModel> getAllProducts() {
        return this.productRepository.findAll();
    }

    public ProductModel getProductById(UUID productId) {
        return this.productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public ProductModel saveProduct(ProductRequestDTO body) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(body, productModel);
        return this.productRepository.save(productModel);

    }

    public  ProductModel updateProduct(UUID productId, ProductRequestDTO body){
        ProductModel productModel = this.productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        BeanUtils.copyProperties(body, productModel);
        return this.productRepository.save(productModel);

    }

    public void deleteProduct(UUID productId){
        ProductModel productModel = this.productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        this.productRepository.delete(productModel);
    }
}
