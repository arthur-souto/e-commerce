package com.e_commerce.store.adapters.in.http;

import com.e_commerce.store.adapters.in.http.request.ExecuteProductOrderRequest;
import com.e_commerce.store.application.command.CreateProductOrderCommand;
import com.e_commerce.store.application.usecase.ProductOrderUseCase;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/orders/context")
public class ProductOrderController {

    private final ProductOrderUseCase productOrderUseCase;

    public ProductOrderController(ProductOrderUseCase productOrderUseCase) {
        this.productOrderUseCase = productOrderUseCase;
    }

    @PostMapping("/execute")
    public void executeOrder(@RequestBody @Valid ExecuteProductOrderRequest req) {
        CreateProductOrderCommand command = new CreateProductOrderCommand(
                req.productId(),
                req.quantity()
        );

        productOrderUseCase.executeOrder(command);
    }
}
