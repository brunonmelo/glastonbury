package br.com.zup.payment.receiver.impl;

import br.com.zup.payment.event.OrderEvent;
import br.com.zup.payment.receiver.OrderReceiver;
import br.com.zup.payment.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderReceiverImpl implements OrderReceiver {

    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    public OrderReceiverImpl(ObjectMapper objectMapper, PaymentService paymentService) {
        this.objectMapper = objectMapper;
        this.paymentService = paymentService;
    }

    @Override
    @KafkaListener(topics = "book-order", groupId = "payment-group-id")
    public void onOrderEvent(String message) throws IOException {
        OrderEvent event = this.objectMapper.readValue(message, OrderEvent.class);
        System.out.println(event.toString());
        paymentService.validatePayment(event);
    }
}
