package br.com.zup.order.receiver.impl;

import br.com.zup.order.event.OrderEvent;
import br.com.zup.order.receiver.OrderResponseReceiver;
import br.com.zup.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OrderResponseReceiverImpl implements OrderResponseReceiver {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    public OrderResponseReceiverImpl(ObjectMapper objectMapper, OrderService orderService) {
        this.objectMapper = objectMapper;
        this.orderService = orderService;
    }

    @KafkaListener(topics = "inventory-order-success", groupId = "order-group-id")
    public void confirmOrder(String message) throws IOException {
        OrderEvent event = this.objectMapper.readValue(message, OrderEvent.class);

        System.out.println("Inventory order success");
        System.out.println(event.toString());

        orderService.processSuccess(event);
    }

    @KafkaListener(topics = "inventory-order-fail", groupId = "order-group-id")
    public void cancelOrder(String message) throws IOException {
        OrderEvent event = this.objectMapper.readValue(message, OrderEvent.class);

        System.out.println("Inventory order fail");
        System.out.println(event.toString());

        orderService.processFail(event);
    }
}
