package br.com.zup.order.service.impl;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.entity.Order;
import br.com.zup.order.event.OrderCreatedEvent;
import br.com.zup.order.event.OrderEvent;
import br.com.zup.order.repository.OrderRepository;
import br.com.zup.order.service.OrderService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderCreatedEvent> template;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            KafkaTemplate<String, OrderCreatedEvent> template) {
        this.orderRepository = orderRepository;
        this.template = template;
    }

    @Override
    public String save(CreateOrderRequest request) {
        String orderId = this.orderRepository.save(request.toEntity()).getId();

        OrderCreatedEvent event = new OrderCreatedEvent(
                orderId,
                request.getCustomerId(),
                request.getAmount(),
                request.getItems());

        this.template.send("created-orders", event);
        System.out.println("Criando order e enviando");

        return orderId;
    }

    @Override
    public List<OrderResponse> findAll() {
        return this.orderRepository.findAll()
                .stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void processFail(OrderEvent event) {
        Order order = orderRepository
                .findById(event.getId()).orElseThrow(() -> new RuntimeException("Id de order não localizado"));

        order.setStatus("canceled");
        orderRepository.save(order);
    }

    @Override
    public void processSuccess(OrderEvent event) {
        Order order = orderRepository
                .findById(event.getId()).orElseThrow(() -> new RuntimeException("Id de order não localizado"));

        order.setStatus("success");
        orderRepository.save(order);
    }
}
