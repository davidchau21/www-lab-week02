package vn.edu.iuh.fit.backend.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.backend.models.ProductImage;
import vn.edu.iuh.fit.backend.repositories.ProductImageRepository;

import java.util.List;

public class ProductImageService {
    @Inject
    private ProductImageRepository dao;

    @Inject
    public ProductImageService(ProductImageRepository productImageDao) {
        this.dao = productImageDao;
    }

    public List<ProductImage> getAll() {
        return dao.getAll();
    }

    public ProductImage searchById(long id) {
        return dao.searchById(id);
    }

    public boolean add(ProductImage productImage) {
        return dao.add(productImage);
    }

    public boolean updateField(long id, String nameField, String newValue) {
        return dao.updateField(id, nameField, newValue);
    }

    public boolean del(long id) {
        return dao.del(id);
    }

}
