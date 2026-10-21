package microservice.order_api.adapter.in.kafka.consumer;


import microservice.order_api.domain.ports.OrderListenerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;


@Component
public class OrderListenerKafkaAdapter implements OrderListenerPort {

    private final Logger logger = LoggerFactory.getLogger(OrderListenerKafkaAdapter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @KafkaListener(topics = "product-created", groupId = "order-service-group-id")
    public void onOrderEvent(String content) {
        logger.info("Received order event from Kafka: {}", content);

        try {
            OrderItemEvent orderItemEvent = objectMapper.readValue(content, OrderItemEvent.class);
            logger.info("Processed order event: {}", orderItemEvent);
        }
        catch (Exception e) {
            logger.error("Error occurred while processing order event", e);
        }
    }
}
