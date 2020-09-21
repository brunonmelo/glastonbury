package br.com.zup.inventory.client.impl;

import br.com.zup.inventory.client.OrderReceiverListener;
import br.com.zup.inventory.event.OrderCreatedEvent;
import br.com.zup.inventory.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OrderReceiverReceiverListenerImpl implements OrderReceiverListener {

    private final ObjectMapper objectMapper;
    private final InventoryService inventoryService;

    public OrderReceiverReceiverListenerImpl(ObjectMapper objectMapper, InventoryService inventoryService) {
        this.objectMapper = objectMapper;
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "created-orders", groupId = "inventory-group-id")
    public void listen(String message) throws IOException {
        OrderCreatedEvent event = this.objectMapper.readValue(message, OrderCreatedEvent.class);
        inventoryService.processOrder(event);
    }
}
