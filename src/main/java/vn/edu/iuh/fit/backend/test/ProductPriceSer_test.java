package vn.edu.iuh.fit.backend.test;

import vn.edu.iuh.fit.backend.models.ProductPrice;
import vn.edu.iuh.fit.backend.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.backend.services.ProductPriceService;
import vn.edu.iuh.fit.backend.services.ProductServices;

import java.time.LocalDateTime;

public class ProductPriceSer_test {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        ProductServices productSer = new ProductServices(productRepository);

        ProductPriceRepository productPriceDao = new ProductPriceRepository();
        ProductPriceService productPriceSer = new ProductPriceService(productPriceDao);
//        boolean hi = productPriceSer.add(
//                new ProductPrice(
//                        LocalDateTime.of(2012,12,21,6,30),
//                        1000.0,
//                        "hi",
//                        productSer.searchById(1)
//                )
//        );
//
//        System.out.println(hi);
        LocalDateTime dateTime = LocalDateTime.of(2012,12,21,6,30);
//        ProductPrice productPrice = productPriceSer.searchById(1, dateTime);
//        System.out.println(productPrice);
    }
}
