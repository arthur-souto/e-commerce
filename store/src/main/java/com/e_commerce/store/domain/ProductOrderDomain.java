package com.e_commerce.store.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductOrderDomain {
    private UUID productId;
    private UUID orderId;
    // snapshot of product information
    private String productName;
    private String productDescription;
    private BigDecimal unitPrice;
    // quantity of the product in the order
    private int quantity;

    public ProductOrderDomain(UUID productId, UUID orderId, int quantity, String productDescription, BigDecimal unitPrice, String productName) {
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.productDescription = productDescription;
        this.unitPrice = unitPrice;
        this.productName = productName;
    }

    public static ProductOrderDomain build(UUID productId, int quantity, String productDescription, BigDecimal unitPrice, String productName) {
        return new ProductOrderDomain(productId, UUID.randomUUID(), quantity, productDescription, unitPrice, productName);
    }

    public UUID getProductId() {
        return productId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
}
