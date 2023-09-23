package vn.edu.iuh.fit.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import vn.edu.iuh.fit.enums.ProductStatus;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private long productId;

    @Column(name = "name", columnDefinition = "varchar(150)")
    private String name;

    @Column(name = "description", columnDefinition = "varchar(250)")
    private String description;

    @Column(name = "unit", columnDefinition = "varchar(50)")
    private String unit;

    @Column(name = "manufacturer_name", columnDefinition = "varchar(100)")
    private String manufacturer_name;

    @Column(name = "status", columnDefinition = "int")
    private ProductStatus status;

    // RELATIONSHIP
    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductPrice> productPrices;

    public Product() {
    }

    public Product(long productId, String name, String description, String unit, String manufacturer_name,
            ProductStatus status) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.manufacturer_name = manufacturer_name;
        this.status = status;
    }

    public Product(String name, String description, String unit, String manufacturer_name, ProductStatus status) {
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.manufacturer_name = manufacturer_name;
        this.status = status;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getManufacturer_name() {
        return manufacturer_name;
    }

    public void setManufacturer_name(String manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", name=" + name + ", description=" + description + ", unit=" + unit
                + ", manufacturer_name=" + manufacturer_name + ", status=" + status + "]";
    }

}
