package br.com.zup.inventory.client;

import java.io.IOException;

public interface OrderReceiverListener {

    void listen(String message) throws IOException;
}
