package vn.edu.iuh.fit.backend.mod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;
import java.util.ArrayList;

import vn.edu.iuh.fit.backend.models.ProductImage;
import vn.edu.iuh.fit.backend.models.ProductPrice;
import vn.edu.iuh.fit.backend.models.Product;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProductModel {
    ObjectMapper objectMapper = new ObjectMapper();

    public List<Product> getAll() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/lab_week02/api/Product";
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class,
                Product.class);

        return objectMapper.readValue(httpResponse.body(), collectionType);

    }

    public List<Product> getFromXtoY(int x, int y) throws URISyntaxException, IOException, InterruptedException {
        String uri = "http://localhost:8080/lab_week02/api/Product/" + x + "-" + y;
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET().build();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class,
                Product.class);

        return objectMapper.readValue(response.body(), collectionType);
    }

    public List<String> getPriceListID(List<Long> listIdProduct)
            throws URISyntaxException, IOException, InterruptedException {
        List<String> listPrice = new ArrayList<>();
        for (long id : listIdProduct) {
            String uri = "http://localhost:8080/lab_week02/api/ProductPrice/" + id + "/1/1";
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .GET().build();
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class,
                    ProductPrice.class);
            List<ProductPrice> productPrices = objectMapper.readValue(response.body(), collectionType);
            listPrice.add(productPrices.get(0).getPrice().toString());
        }

        return listPrice;
    }

    public List<String> getPathIMGListID(List<Long> listIdProduct)
            throws URISyntaxException, IOException, InterruptedException {
        List<String> listPath = new ArrayList<>();
        for (long id : listIdProduct) {
            String uri = "http://localhost:8080/lab_week02/api/ProductImage/" + id;
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .GET().build();
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            ProductImage productImage = objectMapper.readValue(response.body(), ProductImage.class);
            listPath.add(productImage.getPath());
        }

        return listPath;
    }

    public boolean add(Product product) throws URISyntaxException, IOException, InterruptedException {
        String uri = "http://localhost:8080/lab_week02/api/Product";
        String s = objectMapper.writeValueAsString(product);
        System.out.println(s);
        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(s);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .POST(bodyPublisher)
                .header("Content-Type", "application/json")
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body().equals("true");
    }

    public boolean delete(int id) throws URISyntaxException, IOException, InterruptedException {
        String uri = "http://localhost:8080/lab_week02/api/Product/" + id;
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .DELETE().build();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.body().equals("true");
    }
}
