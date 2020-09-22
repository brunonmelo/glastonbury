package br.com.zup.inventory.event;

import br.com.zup.inventory.entity.InventoryItem;
import br.com.zup.inventory.entity.OrderPosted;

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

    public OrderCreatedEvent(String orderId, String customerId, BigDecimal amount, Map<String, Integer> itemsQuantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.itemQuantity = itemsQuantity;
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

    public List<InventoryItem> getInventoryItems() {
        return mapsToEntities(itemQuantity);
    }

    public void setItemQuantity(Map<String, Integer> itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public OrderPosted toEntity() {
        return new OrderPosted(
                this.orderId,
                this.customerId,
                this.amount,
                mapsToEntities(this.itemQuantity));
    }

    private List<InventoryItem> mapsToEntities(Map<String, Integer> itemQuantity) {
        return this.itemQuantity
                .entrySet()
                .stream()
                .map(i -> new InventoryItem(i.getKey(), i.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", amount=" + amount +
                ", itemIds=" + itemQuantity +
                '}';
    }
}
