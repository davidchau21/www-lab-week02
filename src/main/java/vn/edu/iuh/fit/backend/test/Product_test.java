package vn.edu.iuh.fit.backend.test;

import vn.edu.iuh.fit.backend.mod.ProductModel;

import java.io.IOException;
import java.net.URISyntaxException;

public class Product_test {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        ProductModel productModel = new ProductModel();
        productModel.getAll().forEach(p -> {
            System.out.println(p);
        });
    }
}
