package com.e_commerce.store.application.usecase;

import com.e_commerce.store.application.command.CreateProductOrderCommand;
import com.e_commerce.store.domain.ProductDomain;
import com.e_commerce.store.domain.ProductOrderDomain;
import com.e_commerce.store.domain.ports.ExecuteProductOrderMessagingPort;
import com.e_commerce.store.domain.ports.ProductRepository;

public class ProductOrderUseCase {

    private final ExecuteProductOrderMessagingPort executeProductOrderMessagingPort;
    private final ProductRepository productRepository;

    public ProductOrderUseCase(ExecuteProductOrderMessagingPort executeProductOrderMessagingPort, ProductRepository productRepository) {
        this.executeProductOrderMessagingPort = executeProductOrderMessagingPort;
        this.productRepository = productRepository;
    }

    public void executeOrder(CreateProductOrderCommand command) {
        ProductDomain productDomain = productRepository.findByIdOptional(command.productId()).orElseThrow(
                () -> new RuntimeException("Product not found")
        );

        ProductOrderDomain order = ProductOrderDomain.build(
                productDomain.getId(),
                command.quantity(),
                productDomain.getDescription(),
                productDomain.getPrice(),
                productDomain.getName()
        );

        executeProductOrderMessagingPort.executeOrder(order);
    }
}
