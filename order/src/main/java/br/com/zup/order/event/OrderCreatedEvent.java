package br.com.zup.order.event;

import br.com.zup.order.controller.request.CreateOrderRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderCreatedEvent {

    private String orderId;
    private String customerId;
    private BigDecimal amount;
    private Map<String, Integer> itemQuantity;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(String orderId,
                             String customerId,
                             BigDecimal amount,
                             List<CreateOrderRequest.OrderItemPart> itemQuantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.itemQuantity = mapItemsQuantity(itemQuantity);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Map<String, Integer> getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Map<String, Integer> itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    private Map<String, Integer> mapItemsQuantity(List<CreateOrderRequest.OrderItemPart> orderItem) {
        return orderItem
                .stream()
                .collect(Collectors.toMap(
                        CreateOrderRequest.OrderItemPart::getId,
                        CreateOrderRequest.OrderItemPart::getQuantity)
                );
    }
}
