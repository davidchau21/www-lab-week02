package vn.edu.iuh.fit.test;

import vn.edu.iuh.fit.enums.ProductStatus;
import vn.edu.iuh.fit.models.Product;
import vn.edu.iuh.fit.repositories.ProductResponsitory;
import vn.edu.iuh.fit.services.ProductServices;

public class ProductSer_test {
    public static void main(String[] args) {
        ProductResponsitory productResponsitory = new ProductResponsitory();
        ProductServices productServices = new ProductServices(productResponsitory);
        boolean add = productServices.add(new Product(100,
                "ok", "100%", "kg", "ok", ProductStatus.IN_ACTIVE));
        System.out.println(add);

        // Product product = productServices.searchById(100);
        // System.out.println(product);

    }
}
