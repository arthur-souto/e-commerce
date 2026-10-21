package com.e_commerce.store.application.usecase;

import com.e_commerce.store.application.command.CreateProductCommand;
import com.e_commerce.store.application.command.UpdateProductCommand;
import com.e_commerce.store.domain.ProductDomain;
import com.e_commerce.store.domain.ports.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class ProductUseCase {

    private final ProductRepository productRepository;

    public ProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductDomain saveProduct(CreateProductCommand command) {
        ProductDomain productDomain = new ProductDomain();

        productDomain.setName(command.name());
        productDomain.setDescription(command.description());
        productDomain.setPrice(command.price());

        return productRepository.save(productDomain);
    }

    @Transactional
    public ProductDomain updateProduct(UUID id, UpdateProductCommand command) {
        ProductDomain productDomain = productRepository.findById(id);

        if(command.name() != null) {
            productDomain.setName(command.name());
        }
        if(command.description() != null) {
            productDomain.setDescription(command.description());
        }
        if(command.price() != null) {
            productDomain.setPrice(command.price());
        }

        return productRepository.update(productDomain);
    }

    @Transactional(readOnly = true)
    public Page<ProductDomain> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ProductDomain findProductById(UUID id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void deleteProductById(UUID id) {
        productRepository.deleteById(id);
    }
}
