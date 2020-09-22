package br.com.zup.inventory.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "inventory_item")
public class InventoryItem {

    @Id
    private String id;
    private Integer quantity;

    public InventoryItem() {
    }

    public InventoryItem(String id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
