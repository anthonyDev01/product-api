package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRequestDTO;
import com.example.springboot.models.ProductModel;
import com.example.springboot.models.exceptions.ProductNotFoundException;
import com.example.springboot.repositories.ProductRepository;
import com.example.springboot.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRequestDTO body) {
        ProductModel productSaved = this.productService.saveProduct(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
    }

    @GetMapping()
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable UUID idProduct) {
        ProductModel product = this.productService.getProductById(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable UUID idProduct, @RequestBody @Valid ProductRequestDTO body) {
        ProductModel product = this.productService.updateProduct(idProduct, body);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID idProduct) {
        this.productService.deleteProduct(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }

}
