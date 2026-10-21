package com.e_commerce.store.config;

import com.e_commerce.store.adapters.out.ExecuteProductOrderMessagingAdapter;
import com.e_commerce.store.domain.ports.ExecuteProductOrderMessagingPort;
import com.e_commerce.store.domain.ports.SendProductOrderMessagePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Bean
    public ExecuteProductOrderMessagingPort productOrderPort(SendProductOrderMessagePort sendProductOrderMessagePort) {
        return new ExecuteProductOrderMessagingAdapter(sendProductOrderMessagePort);
    }
}
