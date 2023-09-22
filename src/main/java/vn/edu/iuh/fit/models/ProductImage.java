package vn.edu.iuh.fit.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private long image_id;

    @Column(name = "path", length = 250, nullable = false)
    private String path;

    @Column(name = "alternative", length = 250, nullable = false)
    private String alternative;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
