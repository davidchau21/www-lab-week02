package vn.edu.iuh.fit.models;

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
}
