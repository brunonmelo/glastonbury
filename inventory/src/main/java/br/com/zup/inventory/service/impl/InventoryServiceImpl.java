package br.com.zup.inventory.service.impl;

import br.com.zup.inventory.entity.InventoryItem;
import br.com.zup.inventory.event.OrderCreatedEvent;
import br.com.zup.inventory.repository.InventoryRepository;
import br.com.zup.inventory.service.InventoryService;
import event.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final KafkaTemplate<String, OrderEvent> template;

    public InventoryServiceImpl(InventoryRepository repository, KafkaTemplate<String, OrderEvent> template) {
        this.repository = repository;
        this.template = template;
    }

    @Override
    public void processOrder(OrderCreatedEvent orderCreatedEvent) {
        List<InventoryItem> inventoryItems = orderCreatedEvent
                .getItemIds()
                .stream()
                .map((itemsId) -> repository.findById(itemsId).orElse(new InventoryItem()))
                .collect(Collectors.toList());

        boolean isItensValid = validateItems(inventoryItems);

        String orderType = isItensValid ? "inventory-order-success" : "inventory-order-fail";
        OrderEvent orderEvent = createOrderEvent(orderCreatedEvent, isItensValid);

        template.send(orderType, orderEvent);
    }

    private OrderEvent createOrderEvent(OrderCreatedEvent orderCreatedEvent, boolean isItensValid) {
        String message = isItensValid ? "success" : "error on validate items inventory";
        return new OrderEvent(orderCreatedEvent, message);
    }

    private boolean validateItems(List<InventoryItem> inventoryItems) {
        long value = Math.round(Math.random());
        return value != 0;
    }
}
