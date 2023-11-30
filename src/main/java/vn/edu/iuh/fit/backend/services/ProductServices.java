package vn.edu.iuh.fit.backend.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;

import java.util.List;

public class ProductServices {
    @Inject
    private ProductRepository dao;

    @Inject
    public ProductServices(ProductRepository productResponsitory) {
        this.dao = productResponsitory;
    }

    public ProductServices() {
        dao= new ProductRepository();
    }
    public List<Product> getAll() {
        return dao.getAll();
    }

    public Product searchById(long id) {
        return dao.searchById(id);
    }

    public List<Product> getFromXToY(int x, int y) {
        return dao.getFromXToY(x, y);
    };

    public boolean add(Product product) {
        return dao.add(product);
    }

    public boolean updateField(long id, Product product) {
        return dao.updateField(id,product);
    }

    public boolean del(long id) {
        return dao.del(id);
    }

    public List<Product>getActiveProducts(){
        return dao.getActiveProduct();
    }

    public void updateStatus(long id, ProductStatus status) {
        dao.updateStatus(id,status);
    }
}
