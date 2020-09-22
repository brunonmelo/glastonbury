package br.com.zup.payment.receiver;

import java.io.IOException;

public interface OrderReceiver {

    void onOrderEvent(String message) throws IOException;
}
