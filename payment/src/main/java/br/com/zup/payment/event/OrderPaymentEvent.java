package br.com.zup.payment.event;

public class OrderPaymentEvent {

    private String id;
    private String customeId;
    private String message;

    public OrderPaymentEvent(OrderEvent orderEvent, String message) {
        this.id = orderEvent.getId();
        this.customeId = orderEvent.getCustomeId();
        this.message = message;
    }

    public OrderPaymentEvent() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomeId() {
        return customeId;
    }

    public void setCustomeId(String customeId) {
        this.customeId = customeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
