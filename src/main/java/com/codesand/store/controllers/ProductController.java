package com.codesand.store.controllers;

import com.codesand.store.dtos.ProductDto;
import com.codesand.store.entities.Product;
import com.codesand.store.mappers.ProductMapper;
import com.codesand.store.repositories.CategoryRepository;
import com.codesand.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    public final ProductRepository productRepository;
    public final ProductMapper productMapper;
    public final CategoryRepository categoryRepository;

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

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto newProduct,
                                                    UriComponentsBuilder uriBuilder){
        var category = categoryRepository.findById(newProduct.getCategoryId()).orElse(null);
        if(category==null){
            return ResponseEntity.badRequest().build();
        }
        var product = productMapper.toEntity(newProduct);
        product.setCategory(category);
        productRepository.save(product);
        var productDto = productMapper.toDto(product);

        var uri = uriBuilder.path("/product/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto request){
        var product = productRepository.findById(id).orElse(null);
        if(product==null){
            return ResponseEntity.notFound().build();
        }

        productMapper.update(request,product);
        productRepository.save(product);

        return ResponseEntity.ok(productMapper.toDto(product));
    }
}
