package com.e_commerce.store.adapters.out.kafka.producer;

import com.e_commerce.store.adapters.out.kafka.event.ProductOrderEvent;
import com.e_commerce.store.domain.ports.SendProductOrderMessagePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class SendProductOrderMessageKafkaProducer implements SendProductOrderMessagePort {

    private final static Logger logger = LoggerFactory.getLogger(SendProductOrderMessageKafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${kafka-topics.product-created}")
    private String topic;

    public SendProductOrderMessageKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendProductOrderMessage(ProductOrderEvent event) {
        String key = event.orderId().toString();
        try {
            String jsonMessage = objectMapper.writeValueAsString(event);

            kafkaTemplate.send(topic, key, jsonMessage).whenComplete(
                    (result, ex) -> {
                        if(ex != null) {
                            logger.error("Error sending product order message to Kafka: {}", ex.getMessage());
                        } else {
                            logger.info("Product order message sent to Kafka topic {} with key {}, partition {} and offset {}",
                                    result.getRecordMetadata().topic(),
                                    key,
                                    result.getRecordMetadata().partition(),
                                    result.getRecordMetadata().offset());
                        }
                    }
            );
        }
        catch(JsonProcessingException ex) {
            logger.error("Error processing product order message as JSON: {}", ex.getMessage());
        }

    }
}
