package vn.edu.iuh.fit.frontend.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.frontend.model.CustomerModel;
import vn.edu.iuh.fit.frontend.model.EmployeeModel;
import vn.edu.iuh.fit.frontend.model.ProductModel;

import java.io.IOException;
import java.util.List;

@WebServlet("/controls")
public class ServletController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object actionObject = req.getParameter("action");
            if (actionObject != null) {
                String action = actionObject.toString();
                if (action.equals("insertEmp")) {
                    EmployeeModel empModel = new EmployeeModel();
                    empModel.insertEmp(req, resp);
                } else if (action.equals("insertCust")) {
                    CustomerModel customerModel = new CustomerModel();
                    customerModel.insertCust(req, resp);
                } else if (action.equals("insert_products")) {
                    ProductModel pm = new ProductModel();
                    pm.insertProduct(req, resp);
                }else if (action.startsWith("edit_product&id=")) {
                    req.getRequestDispatcher("editProduct.jsp").forward(req, resp);
                } else if (action.equals("update_products")) {
                    // Handle updateProduct
                    ProductModel pm = new ProductModel();
                    pm.updateProduct(req, resp);
                } else if(action.startsWith("edit_cust&id=")){
                    req.getRequestDispatcher("editCustomer.jsp").forward(req,resp);
                } else if (action.equals("update_customer")){
                    CustomerModel cust = new CustomerModel();
                    cust.updateCustomer(req,resp);
                }

            } else {
                resp.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object actionObject = req.getParameter("action");
            if (actionObject != null) {
                String action = actionObject.toString();
                if (action.startsWith("edit_product")) {
                    String idParam = req.getParameter("id");
                    if (idParam != null && !idParam.isEmpty()) {
                        long id = Long.parseLong(idParam);

                        // Call the getProductById method
                        ProductModel pm = new ProductModel();
                        pm.getProductById(req, resp);

                    } else {
                        // Handle the case where id is not provided
                        // You may redirect or display an error message
                        resp.sendRedirect("error.jsp");
                    }
                } else if (action.equals("delete_product")) {
                    ProductModel pm = new ProductModel();
                    pm.deleteProduct(req, resp);
                    resp.sendRedirect("products.jsp");
                } else if (action.equals("delete_cust")) {
                    CustomerModel cus = new CustomerModel();
                    cus.deleteCus(req, resp);
                    resp.sendRedirect("customerListing.jsp");
                } else if (action.startsWith("edit_cust")) {
                    String idParam = req.getParameter("id");
                    if (idParam != null && !idParam.isEmpty()) {
                        long id = Long.parseLong(idParam);

                        // Call the editCustomer method
                        CustomerModel customerModel = new CustomerModel();
                        customerModel.getCusById(req, resp);

                    } else {
                        // Handle the case where id is not provided
                        // You may redirect or display an error message
                        resp.sendRedirect("error.jsp");
                    }
                }
            } else {
                resp.sendRedirect("test.jsp");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
