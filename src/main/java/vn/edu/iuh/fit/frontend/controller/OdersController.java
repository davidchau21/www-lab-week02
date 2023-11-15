package vn.edu.iuh.fit.frontend.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.frontend.model.ProductModel;

import java.util.List;
import java.util.ArrayList;

@WebServlet(name = "OdersController", urlPatterns = { "/OdersController" })
public class OdersController extends HttpServlet {
    ProductModel productModel = new ProductModel();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "":
                    List<Product> fromXtoY = productModel.getFromXtoY(1, 5);
                    List<Long> listID = new ArrayList<>();
                    fromXtoY.forEach(p -> {
                        listID.add(p.getId());
                    });

                    request.setAttribute("list_product", fromXtoY);
                    request.setAttribute("list_pathImg", productModel.getPathIMGListID(listID));
                    request.setAttribute("list_price", productModel.getPriceListID(listID));

                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/buy_product.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                case "kl":
                    break;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
