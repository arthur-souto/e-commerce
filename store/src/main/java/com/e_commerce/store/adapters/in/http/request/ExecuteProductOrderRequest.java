package com.e_commerce.store.adapters.in.http.request;

import java.util.UUID;

public record ExecuteProductOrderRequest(
        UUID productId,
        int quantity
) {
}
