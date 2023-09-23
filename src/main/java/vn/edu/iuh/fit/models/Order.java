package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private long id;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @ManyToOne
    @Column(name = "emp_id", length = 150, nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "cust_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(long id, Date orderDate, Employee employee, Customer customer) {
        this.id = id;
        this.orderDate = orderDate;
        this.employee = employee;
        this.customer = customer;
    }

    public Order(Date orderDate, Employee employee, Customer customer) {
        this.orderDate = orderDate;
        this.employee = employee;
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Order))
            return false;
        Order orders = (Order) o;
        return Objects.equals(employee, orders.employee) && Objects.equals(customer, orders.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, customer);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", orderDate=" + orderDate + ", employee=" + employee + ", customer=" + customer
                + "]";
    }

}
