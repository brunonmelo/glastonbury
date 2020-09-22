package br.com.zup.payment.event;

import java.math.BigDecimal;
import java.util.Map;

public class OrderEvent {

    private String id;
    private String customeId;
    private BigDecimal amount;
    private Map<String, Integer> itemIds;
    private String message;

    public OrderEvent() {
    }

    public OrderEvent(String id, String customeId, BigDecimal amount, Map<String, Integer> itemIds, String message) {
        this.id = id;
        this.customeId = customeId;
        this.amount = amount;
        this.itemIds = itemIds;
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

    public Map<String, Integer> getItemIds() {
        return itemIds;
    }

    public String getMessage() {
        return message;
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
