package com.codesand.store.controllers;

import com.codesand.store.dtos.ProductDto;
import com.codesand.store.entities.Product;
import com.codesand.store.mappers.ProductMapper;
import com.codesand.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    public final ProductRepository productRepository;
    public final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getAllProducts(@RequestParam(name = "categoryId", required = false) Byte categoryId){

        List<Product> products;
        if(categoryId != null){
            products = productRepository.findByCategoryId(categoryId);
        }else{
            products = productRepository.findAllWithCategory();
        }

        return products
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable Long id){
        var product = productRepository.findById(id).orElse(null);
        if(product==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }
}
