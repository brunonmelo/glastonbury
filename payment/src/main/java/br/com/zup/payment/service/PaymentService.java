package br.com.zup.payment.service;

import br.com.zup.payment.event.OrderEvent;

public interface PaymentService {
    void validatePayment(OrderEvent event);
}
