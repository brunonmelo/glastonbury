package br.com.zup.inventory.receiver.impl;

import br.com.zup.inventory.event.OrderCreatedEvent;
import br.com.zup.inventory.receiver.OrderReceiver;
import br.com.zup.inventory.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OrderReceiverImpl implements OrderReceiver {

    private final ObjectMapper objectMapper;
    private final InventoryService inventoryService;

    public OrderReceiverImpl(ObjectMapper objectMapper, InventoryService inventoryService) {
        this.objectMapper = objectMapper;
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "created-orders", groupId = "inventory-group-id")
    public void receiveEvent(String message) throws IOException {
        OrderCreatedEvent event = this.objectMapper.readValue(message, OrderCreatedEvent.class);
        System.out.println(event.toString());
        inventoryService.processOrder(event);
    }
}
