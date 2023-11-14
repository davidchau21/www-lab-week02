package vn.edu.iuh.fit.backend.services;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.backend.models.ProductPrice;
import vn.edu.iuh.fit.backend.repositories.ProductPriceRepository;

import java.util.List;

public class ProductPriceService {
    @Inject
    private ProductPriceRepository dao;

    @Inject
    public ProductPriceService(ProductPriceRepository productPriceDao) {
        this.dao = productPriceDao;
    }

    public ProductPrice searchById(ProductPrice productPrice) {
        return dao.searchById(productPrice);
    }

    public List<ProductPrice> getAll(long id) {
        return dao.getAll(id);
    }

    public List<ProductPrice> getFromXToY(long id, int x, int y) {
        return dao.getFromXToY(id, x, y);
    }

    public boolean add(ProductPrice id) {
        return dao.add(id);
    }

    public boolean updateField(ProductPrice id, String nameField, String newValue) {
        return dao.updateField(id, nameField, newValue);
    }

    public boolean del(ProductPrice id) {
        return dao.del(id);
    }

}
