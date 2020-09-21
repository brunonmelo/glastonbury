package br.com.zup.inventory.event;

import br.com.zup.inventory.entity.OrderPosted;

import java.math.BigDecimal;
import java.util.List;

public class OrderCreatedEvent {

    private String orderId;
    private String customerId;
    private BigDecimal amount;
    private List<String> itemIds;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(String orderId, String customerId, BigDecimal amount, List<String> itemIds) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.itemIds = itemIds;
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

    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

    public OrderPosted toEntity() {
        return new OrderPosted(
                this.orderId,
                this.customerId,
                this.amount,
                this.itemIds);
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", amount=" + amount +
                ", itemIds=" + itemIds +
                '}';
    }
}
