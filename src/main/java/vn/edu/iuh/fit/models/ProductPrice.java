package vn.edu.iuh.fit.models;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_price")
public class ProductPrice {
    @Id
    @Column(name = "price_date_time", length = 50, nullable = false)
    private DateTime price_date_time;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "note", length = 250, nullable = false)
    private String note;

}
