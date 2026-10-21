package com.e_commerce.store.application.command;

import java.util.UUID;

public record CreateProductOrderCommand(
        UUID productId,
        int quantity
) {
}
