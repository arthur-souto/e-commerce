package microservice.order_api.adapter.in.kafka.consumer;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemEvent(
        UUID productId,
        UUID orderId,
        String productName,
        String productDescription,
        BigDecimal unitPrice,
        int quantity
) {
}
