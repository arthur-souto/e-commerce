package com.e_commerce.store.adapters.in.http;

import com.e_commerce.store.adapters.in.http.request.CreateProductRequest;
import com.e_commerce.store.adapters.in.http.request.UpdateProductRequest;
import com.e_commerce.store.application.command.CreateProductCommand;
import com.e_commerce.store.application.command.UpdateProductCommand;
import com.e_commerce.store.application.usecase.ProductUseCase;
import com.e_commerce.store.domain.ProductDomain;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/products/context")
public class ProductController {

    private final ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid CreateProductRequest req) {
        CreateProductCommand command = new CreateProductCommand(
                req.name(),
                req.description(),
                req.price()
        );

        ProductDomain product = productUseCase.saveProduct(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable UUID id, @RequestBody @Valid UpdateProductRequest req) {
        UpdateProductCommand command = new UpdateProductCommand(
                req.name() != null ? req.name() : null,
                req.description() != null ? req.description() : null,
                req.price() != null ? req.price() : null
        );

        ProductDomain product = productUseCase.updateProduct(id,command);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/paginated/all")
    public ResponseEntity<?> getAllProducts(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(productUseCase.findAllProducts(pageable));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getProductById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(productUseCase.findProductById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable UUID id) {
        productUseCase.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
