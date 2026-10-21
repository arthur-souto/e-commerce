package microservice.order_api.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItemDomain {
    private UUID productId;
    private UUID orderId;
    private String productName;
    private String productDescription;
    private BigDecimal unitPrice;
    private int quantity;

    public OrderItemDomain(UUID productId, UUID orderId, String productName, String productDescription, BigDecimal unitPrice, int quantity) {
        this.productId = productId;
        this.orderId = orderId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
