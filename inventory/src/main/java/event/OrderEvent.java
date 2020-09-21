package event;

import br.com.zup.inventory.event.OrderCreatedEvent;

import java.math.BigDecimal;
import java.util.List;

public class OrderEvent {

    private final String id;
    private final String customeId;
    private final BigDecimal amount;
    private final List<String> itemIds;
    private final String message;

    public OrderEvent(OrderCreatedEvent orderCreatedEvent, String message) {
        this.id = orderCreatedEvent.getOrderId();
        this.customeId = orderCreatedEvent.getCustomerId();
        this.amount = orderCreatedEvent.getAmount();
        this.itemIds = orderCreatedEvent.getItemIds();
        this.message = message;
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
}
