package vn.edu.iuh.fit.frontend.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.models.ProductImage;
import vn.edu.iuh.fit.backend.models.ProductPrice;
import vn.edu.iuh.fit.backend.services.ProductServices;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    ObjectMapper objectMapper = new ObjectMapper();
    private final ProductServices services =new ProductServices();
    public void insertProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String unit = req.getParameter("unit");
        String manu = req.getParameter("manu");
        String status = req.getParameter("status");

        Product product =new Product(name,desc,unit,manu, ProductStatus.valueOf(status));
        services.add(product);
        // xu ly sau khi insert
        resp.sendRedirect("products.jsp");
    }

    public void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        long id =Long.parseLong(req.getParameter("id"));
        services.del(id);
        resp.sendRedirect("products.jsp");
    }

    public void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String unit = req.getParameter("unit");
        String manu = req.getParameter("manu");
        String status = req.getParameter("status");

        Product product = new Product(name, desc, unit, manu, ProductStatus.valueOf(status));

        // Pass the entire product object to the updateField method
        boolean updated = services.updateField(id, product);

        // Check if the update was successful before redirecting
        if (updated) {
            resp.sendRedirect("products.jsp");
        } else {
            // Handle the case where the update failed
            // You may want to redirect to an error page or log the error
            resp.sendRedirect("error.jsp");
        }
    }


    public List<Product> getAll() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/lab_week02/products";
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

    public void getProductById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));

            // Retrieve the product by ID
            Product product = services.searchById(id);

            if (product != null) {
                // Set the product as an attribute in the request
                req.setAttribute("product", product);

                // Forward to the editProduct.jsp for editing
                req.getRequestDispatcher("editProduct.jsp").forward(req, resp);
            } else {
                // Handle the case where the product is not found
                resp.sendRedirect("error.jsp");
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid long
            resp.sendRedirect("error.jsp");
        } catch (Exception e) {
            // Handle other exceptions
            throw new ServletException("Error getting product by ID", e);
        }
    }

}
