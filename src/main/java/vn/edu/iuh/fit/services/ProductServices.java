package vn.edu.iuh.fit.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.models.Product;
import vn.edu.iuh.fit.repositories.ProductResponsitory;

import java.util.List;

public class ProductServices {
    @Inject
    private ProductResponsitory dao;

    @Inject
    public ProductServices(ProductResponsitory productResponsitory) {
        this.dao = productResponsitory;
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

    public boolean updateField(long id, String nameField, String newValue) {
        return dao.updateField(id, nameField, newValue);
    }

    public boolean del(long id) {
        return dao.del(id);
    }

}
