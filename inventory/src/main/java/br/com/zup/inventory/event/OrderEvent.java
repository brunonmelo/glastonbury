package br.com.zup.inventory.event;

import br.com.zup.inventory.entity.InventoryItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderEvent {

    private final String id;
    private final String customeId;
    private final BigDecimal amount;
    private final Map<String, Integer> itemIds;
    private final String message;

    public OrderEvent(OrderCreatedEvent orderCreatedEvent, String message) {
        this.id = orderCreatedEvent.getOrderId();
        this.customeId = orderCreatedEvent.getCustomerId();
        this.amount = orderCreatedEvent.getAmount();
        this.itemIds = ListInventoryItemsToMap(orderCreatedEvent.getInventoryItems());
        this.message = message;
    }

    private Map<String, Integer> ListInventoryItemsToMap(List<InventoryItem> items) {
        return items.stream().collect(Collectors.toMap(InventoryItem::getId, InventoryItem::getQuantity));
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

    public Map<String, Integer> getItemIds() {
        return itemIds;
    }

    public String getMessage() {
        return message;
    }
}
