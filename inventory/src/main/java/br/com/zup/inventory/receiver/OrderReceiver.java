package br.com.zup.inventory.receiver;

import java.io.IOException;

public interface OrderReceiver {
    void receiveEvent(String message)  throws IOException;
}
