package br.com.zup.inventory.service.impl;

import br.com.zup.inventory.entity.InventoryItem;
import br.com.zup.inventory.event.OrderCreatedEvent;
import br.com.zup.inventory.event.OrderEvent;
import br.com.zup.inventory.exceptions.InventoryException;
import br.com.zup.inventory.exceptions.ItemNotFoundException;
import br.com.zup.inventory.exceptions.NotEnoughItemException;
import br.com.zup.inventory.repository.InventoryRepository;
import br.com.zup.inventory.service.InventoryService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final KafkaTemplate<String, OrderEvent> template;

    public InventoryServiceImpl(
            InventoryRepository repository,
            KafkaTemplate<String, OrderEvent> template) {
        this.repository = repository;
        this.template = template;
    }

    @Override
    public void processOrder(OrderCreatedEvent orderCreatedEvent) {
        String orderType;
        OrderEvent orderEvent;
        try {
            validateEventItems(orderCreatedEvent);

            orderEvent = createValidOrderEvent(orderCreatedEvent);
            orderType = "book-order";
        } catch (InventoryException e) {
            orderEvent = createInvalidOrderEvent(orderCreatedEvent);
            orderType = "order-fail";
        }

        template.send(orderType, orderEvent);
        System.out.println("Enviando Evento " + orderType);
    }

    private OrderEvent createValidOrderEvent(OrderCreatedEvent orderCreatedEvent) {
        String message = "success";
        return new OrderEvent(orderCreatedEvent, message);
    }

    private OrderEvent createInvalidOrderEvent(OrderCreatedEvent orderCreatedEvent) {
        String message = "error on validate items inventory";
        return new OrderEvent(orderCreatedEvent, message);
    }

    private void validateEventItems(OrderCreatedEvent orderCreatedEvent) {
//        List<InventoryItem> inventoryItems = orderCreatedEvent
//                .getInventoryItems()
//                .stream()
//                .map(i -> repository.findById(i.getId()).orElseThrow(() -> new ItemNotFoundException("Item not found")))
//                .collect(Collectors.toList());

        double random = Math.random();
        if(random < 0.3) throw new NotEnoughItemException("Not enough items available");
    }
}
