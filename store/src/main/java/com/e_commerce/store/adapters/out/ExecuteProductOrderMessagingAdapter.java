package com.e_commerce.store.adapters.out;

import com.e_commerce.store.adapters.out.kafka.event.ProductOrderEvent;
import com.e_commerce.store.domain.ProductOrderDomain;
import com.e_commerce.store.domain.ports.ExecuteProductOrderMessagingPort;
import com.e_commerce.store.domain.ports.SendProductOrderMessagePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteProductOrderMessagingAdapter implements ExecuteProductOrderMessagingPort {

    private final Logger logger = LoggerFactory.getLogger(ExecuteProductOrderMessagingAdapter.class);

    private final SendProductOrderMessagePort sendProductOrderMessagePort;

    public ExecuteProductOrderMessagingAdapter(SendProductOrderMessagePort sendProductOrderMessagePort) {
        this.sendProductOrderMessagePort = sendProductOrderMessagePort;
    }

    @Override
    public void executeOrder(ProductOrderDomain productOrderDomain) {
        logger.info("Executing order for product: {} with quantity: {} and orderId: {}",
                productOrderDomain.getProductName(),
                productOrderDomain.getQuantity(),
                productOrderDomain.getOrderId()
        );

        ProductOrderEvent event = new ProductOrderEvent(
                productOrderDomain.getProductId(),
                productOrderDomain.getOrderId(),
                productOrderDomain.getProductName(),
                productOrderDomain.getProductDescription(),
                productOrderDomain.getUnitPrice(),
                productOrderDomain.getQuantity()
        );

        logger.info("Sending product order message to Kafka for orderId: {}", productOrderDomain.getOrderId());

        sendProductOrderMessagePort.sendProductOrderMessage(event);
    }
}
