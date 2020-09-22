package br.com.zup.payment.service.impl;

import br.com.zup.payment.event.OrderEvent;
import br.com.zup.payment.event.OrderPaymentEvent;
import br.com.zup.payment.exceptions.ValidatePaymentException;
import br.com.zup.payment.service.PaymentService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final KafkaTemplate<String, OrderPaymentEvent> template;

    public PaymentServiceImpl(KafkaTemplate<String, OrderPaymentEvent> template) {
        this.template = template;
    }

    @Override
    public void validatePayment(OrderEvent orderEvent) {
        OrderPaymentEvent orderPaymentEvent;
        String topic;

        try {
            validatePaymentEngine(orderEvent);

            orderPaymentEvent = createSuccessOrderPaymentEvent(orderEvent);
            topic = "order-success";
        } catch (ValidatePaymentException e) {
            orderPaymentEvent = createFailOrderPaymentEvent(orderEvent, e.getMessage());
            topic = "order-fail";
        }

        template.send(topic, orderPaymentEvent);
        System.out.println("Enviando Evento Payment " + topic);
    }

    private OrderPaymentEvent createSuccessOrderPaymentEvent(OrderEvent orderEvent) {
        return new OrderPaymentEvent(orderEvent, "success");
    }

    private OrderPaymentEvent createFailOrderPaymentEvent(OrderEvent orderEvent, String message) {
        return new OrderPaymentEvent(orderEvent, message);
    }

    private void validatePaymentEngine(OrderEvent event) {
        double random = Math.random();
        if(random < 0.3) throw new ValidatePaymentException("Payment Problems");
    }

}
