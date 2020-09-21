package br.com.zup.order.receiver;

import java.io.IOException;

public interface OrderResponseReceiver {
    void confirmOrder(String event) throws IOException;

    void cancelOrder(String event) throws IOException;
}
