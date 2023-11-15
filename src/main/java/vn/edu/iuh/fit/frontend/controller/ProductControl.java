package vn.edu.iuh.fit.frontend.controller;

import vn.edu.iuh.fit.backend.enums.ProductStatus;

import vn.edu.iuh.fit.backend.models.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.frontend.model.ProductModel;

import java.io.IOException;
import java.net.URISyntaxException;

@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet {
    ProductModel productModel = new ProductModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("1 " + action);
        try {
            switch (action) {
                case "":
                    req.setAttribute("list", productModel.getAll());

                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/product-crud.jsp");
                    requestDispatcher.forward(req, resp);
                    break;
                case "insert":
                    insert(req, resp);
                    break;
                case "delete":
                    delete(req, resp);
                    break;
                // case "Anal-Year-Month-Day":
                // System.out.println("2");
                // analYearMonthDay(req, resp);
                // break;
                // case "Anal-Years-Months":
                // analYearMonthDay(req, resp);
                // break;
            }

        } catch (URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws URISyntaxException, IOException, InterruptedException, ServletException {
        String id = req.getParameter("id");
        boolean delete = productModel.delete(Integer.parseInt(id));
        if (delete) {
            req.setAttribute("result", "true");
        } else
            req.setAttribute("result", "false");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ControlServlet?action=product_crud");
        requestDispatcher.forward(req, resp);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String unit = req.getParameter("unit");
        String manufacturer = req.getParameter("manufacturer");
        String status = req.getParameter("status");

        ProductStatus productStatus = null;
        if (status.equals("-1"))
            productStatus = ProductStatus.TEMINATED;
        else if (status.equals("0"))
            productStatus = ProductStatus.IN_ACTIVE;
        else
            productStatus = ProductStatus.ACTIVE;

        Product product = new Product(
                name, description, unit, manufacturer, productStatus);
        boolean add = false;
        try {
            add = productModel.add(product);
        } catch (URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
        if (add) {
            req.setAttribute("result", "true");
        } else
            req.setAttribute("result", "false");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ControlServlet?action=product_crud");
        requestDispatcher.forward(req, resp);
    }
}
