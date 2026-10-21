package com.e_commerce.store.adapters.out.kafka.event;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductOrderEvent(
        UUID productId,
        UUID orderId,
        // snapshot of product information
        String productName,
        String productDescription,
        BigDecimal unitPrice,
        // quantity of the product in the order
        int quantity
) {
}
