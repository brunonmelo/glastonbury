package br.com.zup.inventory.exceptions;

public abstract class InventoryException extends RuntimeException {
    public InventoryException(String msg) {
        super(msg);
    }
}
