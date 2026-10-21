package com.e_commerce.store.config;

import com.e_commerce.store.adapters.out.persistence.adapter.ProductRepositoryAdapter;
import com.e_commerce.store.adapters.out.persistence.repository.ProductJpaRepository;
import com.e_commerce.store.domain.ports.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {

    @Bean
    public ProductRepository productRepository(ProductJpaRepository productJpaRepository) {
        return new ProductRepositoryAdapter(productJpaRepository);
    }
}
