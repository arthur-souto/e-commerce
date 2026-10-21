package com.e_commerce.store.domain.ports;

import com.e_commerce.store.adapters.out.kafka.event.ProductOrderEvent;

public interface SendProductOrderMessagePort {
    public void sendProductOrderMessage(ProductOrderEvent event);
}
