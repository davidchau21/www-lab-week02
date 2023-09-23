package vn.edu.iuh.fit.models;

import java.util.Objects;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_price")
public class ProductPrice implements java.io.Serializable {
    @Id
    @Column(name = "price_date_time")
    private DateTime price_date_time;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "price")
    private double price;

    @Column(name = "note")
    private String note;

    public ProductPrice() {
    }

    public ProductPrice(DateTime price_date_time, Product product, double price, String note) {
        this.price_date_time = price_date_time;
        this.product = product;
        this.price = price;
        this.note = note;
    }

    public ProductPrice(DateTime price_date_time, Product product) {
        this.price_date_time = price_date_time;
        this.product = product;
    }

    public DateTime getPrice_date_time() {
        return price_date_time;
    }

    public void setPrice_date_time(DateTime price_date_time) {
        this.price_date_time = price_date_time;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
        return "ProductPrice [price_date_time=" + price_date_time + ", product=" + product + ", price=" + price
                + ", note=" + note + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ProductPrice))
            return false;
        ProductPrice that = (ProductPrice) o;
        return Objects.equals(price_date_time, that.price_date_time) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price_date_time, product);
    }
}
