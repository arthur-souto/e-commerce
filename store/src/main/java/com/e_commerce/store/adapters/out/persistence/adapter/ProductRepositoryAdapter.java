package com.e_commerce.store.adapters.out.persistence.adapter;

import com.e_commerce.store.adapters.out.persistence.entity.ProductEntity;
import com.e_commerce.store.adapters.out.persistence.repository.ProductJpaRepository;
import com.e_commerce.store.domain.ProductDomain;
import com.e_commerce.store.domain.ports.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    public ProductRepositoryAdapter(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public ProductDomain save(ProductDomain productDomain) {
        final var product = this.toEntity(productDomain);
        final var savedProduct = productJpaRepository.save(product);
        return this.toDomain(savedProduct);
    }

    @Override
    public Page<ProductDomain> findAll(Pageable pageable) {
        return productJpaRepository.findAll(pageable).map(this::toDomain);
    }

    @Override
    public ProductDomain findById(UUID id) {
        final var product = productJpaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
        return this.toDomain(product);
    }

    @Override
    public Optional<ProductDomain> findByIdOptional(UUID id) {
        return productJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public boolean existsById(UUID id) {
        return productJpaRepository.existsById(id);
    }

    @Override
    public ProductDomain update(ProductDomain productDomain) {
        final var product = this.toEntity(productDomain);
        final var updatedProduct = productJpaRepository.save(product);
        return this.toDomain(updatedProduct);
    }

    @Override
    public void deleteById(UUID id) {
        productJpaRepository.deleteById(id);
    }

    private ProductEntity toEntity(ProductDomain productDomain) {
        return ProductEntity.builder()
                .id(productDomain.getId())
                .name(productDomain.getName())
                .description(productDomain.getDescription())
                .price(productDomain.getPrice())
                .build();
    }

    private ProductDomain toDomain(ProductEntity productEntity) {
        return new ProductDomain(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice()
        );
    }
}
