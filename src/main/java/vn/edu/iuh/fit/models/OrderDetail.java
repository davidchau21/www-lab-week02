package vn.edu.iuh.fit.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderDetail {
    @Id
    @ManyToOne
    @Column(name = "order_id", nullable = false)
    private Order order;

    @Id
    @Column(name = "product_id", nullable = false)
    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private long quantity;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private String note;

    public OrderDetail() {
    }

    public OrderDetail(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    public OrderDetail(Product product, long quantity, long price, String note) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.note = note;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "OrderDetail [order=" + order + ", product=" + product + ", quantity=" + quantity + ", price=" + price
                + ", note=" + note + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof OrderDetail))
            return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
