package br.com.zup.inventory.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity(name = "order_posted")
public class OrderPosted {

    @Id
    private String id;

    private String customerId;
    private BigDecimal amount;

    @ManyToMany(targetEntity = InventoryItem.class, fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<String> items;

    public OrderPosted(String id, String customerId, BigDecimal amount, List<String> items) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.items = items;
    }

    public OrderPosted() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPosted orderPosted = (OrderPosted) o;
        return Objects.equals(id, orderPosted.id) &&
                Objects.equals(customerId, orderPosted.customerId) &&
                Objects.equals(amount, orderPosted.amount) &&
                Objects.equals(items, orderPosted.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, amount, items);
    }
}
