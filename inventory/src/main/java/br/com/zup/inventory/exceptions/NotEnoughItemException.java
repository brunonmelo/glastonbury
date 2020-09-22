package br.com.zup.inventory.exceptions;

public class NotEnoughItemException extends InventoryException {
    public NotEnoughItemException(String msg) {
        super(msg);
    }
}
