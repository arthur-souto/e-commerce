package com.e_commerce.store.config;

import com.e_commerce.store.adapters.out.kafka.event.ProductOrderEvent;
import com.e_commerce.store.application.usecase.ProductOrderUseCase;
import com.e_commerce.store.application.usecase.ProductUseCase;
import com.e_commerce.store.domain.ports.ExecuteProductOrderMessagingPort;
import com.e_commerce.store.domain.ports.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ProductUseCase productUseCase(ProductRepository productRepository) {
        return new ProductUseCase(productRepository);
    }

    @Bean
    public ProductOrderUseCase productOrderUseCase(ExecuteProductOrderMessagingPort productOrderEvent, ProductRepository productRepository) {
        return new ProductOrderUseCase(productOrderEvent, productRepository);
    }
}
