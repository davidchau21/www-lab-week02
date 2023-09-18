package vn.edu.iuh.fit.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @Column(name = "product_id", nullable = false)
    private long id;

    @Column(name = "name")
    private String product_name;

    @Column(unique = true, length = 150)
    private String email;

    @Column(length = 15, nullable = false)
    private int phone;

    @Column(length = 120, nullable = false)
    private String address;
}
