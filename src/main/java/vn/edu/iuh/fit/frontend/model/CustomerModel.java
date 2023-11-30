package vn.edu.iuh.fit.frontend.model;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Customer;
import vn.edu.iuh.fit.backend.services.CustomerServices;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    private final CustomerServices services = new CustomerServices();

    public void insertCust(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        Customer customer = new Customer(name, email, phone, address);
        services.add(customer);
        //xu ly sau khi insert
        resp.sendRedirect("customerListing.jsp");
    }

    public List<Customer> getAllCustomer() {
        return services.getAll();
    }

    public void deleteCus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id =Long.parseLong(req.getParameter("id"));
        services.del(id);
        resp.sendRedirect("customerListing.jsp");
    }
    public void getCusById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long customerId = Long.parseLong(req.getParameter("id"));

            // Assuming you have a service method to get a customer by ID
            Customer customer = services.searchById(customerId);

            if (customer != null) {
                // Set the customer as an attribute in the request
                req.setAttribute("customer", customer);

                // Forward to the editCustomer.jsp for editing
                req.getRequestDispatcher("editCustomer.jsp").forward(req, resp);
            } else {
                // Handle the case where the customer is not found
                resp.sendRedirect("error.jsp");
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid long
            resp.sendRedirect("error.jsp");
        } catch (Exception e) {
            // Handle other exceptions
            throw new ServletException("Error getting customer by ID", e);
        }
    }

    public void updateCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id =Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        Customer customer = new Customer(name, email, phone, address);
        services.updateField(id,customer);
        //xu ly sau khi insert
        resp.sendRedirect("customerListing.jsp");
    }
}
