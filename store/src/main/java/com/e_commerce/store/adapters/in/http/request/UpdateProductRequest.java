package com.e_commerce.store.adapters.in.http.request;

import jakarta.validation.constraints.Min;
import java.math.BigDecimal;

public record UpdateProductRequest(
        String name,
        String description,
        @Min(value = 1, message = "Price must be a positive value")
        BigDecimal price
) {

}
