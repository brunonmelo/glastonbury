package br.com.zup.inventory.exceptions;

public class ItemNotFoundException extends InventoryException {
    public ItemNotFoundException(String msg) {
        super(msg);
    }
}
