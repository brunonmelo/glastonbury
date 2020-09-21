package br.com.zup.order.event;

import java.math.BigDecimal;
import java.util.List;

public class OrderEvent {

    private String id;
    private String customeId;
    private BigDecimal amount;
    private List<String> itemIds;
    private String message;

    public OrderEvent(OrderCreatedEvent orderCreatedEvent, String message) {
        this.id = orderCreatedEvent.getOrderId();
        this.customeId = orderCreatedEvent.getCustomerId();
        this.amount = orderCreatedEvent.getAmount();
        this.itemIds = orderCreatedEvent.getItemIds();
        this.message = message;
    }

    public OrderEvent() {
    }

    public String getId() {
        return id;
    }

    public String getCustomeId() {
        return customeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public List<String> getItemIds() {
        return itemIds;
    }

    public String getMessage() {
        return message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomeId(String customeId) {
        this.customeId = customeId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OrderEvent{" +
                "id='" + id + '\'' +
                ", customeId='" + customeId + '\'' +
                ", amount=" + amount +
                ", itemIds=" + itemIds +
                ", message='" + message + '\'' +
                '}';
    }
}
