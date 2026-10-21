package com.e_commerce.store.domain.ports;

import com.e_commerce.store.domain.ProductDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    ProductDomain save(ProductDomain productDomain);
    Page<ProductDomain> findAll(Pageable pageable);
    ProductDomain findById(UUID id);
    Optional<ProductDomain> findByIdOptional(UUID id);
    boolean existsById(UUID id);
    ProductDomain update(ProductDomain productDomain);
    void deleteById(UUID id);
}
