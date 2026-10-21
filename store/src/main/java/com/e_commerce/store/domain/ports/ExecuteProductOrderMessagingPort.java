package com.e_commerce.store.domain.ports;

import com.e_commerce.store.domain.ProductOrderDomain;

public interface ExecuteProductOrderMessagingPort {
    void executeOrder(ProductOrderDomain productOrderDomain);
}
