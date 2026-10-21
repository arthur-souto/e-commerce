package microservice.order_api.domain.ports;

public interface OrderListenerPort {
    void onOrderEvent(String content);
}
