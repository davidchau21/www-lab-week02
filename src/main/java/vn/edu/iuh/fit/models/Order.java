package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private long id;

    @Column(name = "order_id", nullable = false)
    private Date orderDate;

    @ManyToOne
    @Column(name = "emp_id", length = 150, nullable = false)
    private Employee employee;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;


}
