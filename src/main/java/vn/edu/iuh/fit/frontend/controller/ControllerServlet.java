package vn.edu.iuh.fit.frontend.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ControllerServlet", urlPatterns = { "/ControllerServlet" })
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher dispatcher = null;
        switch (action) {
            case "product_crud":
                dispatcher = request.getRequestDispatcher("/ProductControl?action=");
                dispatcher.forward(request, response);
                break;
            case "orders_Anal-Year-Month-Day":
                dispatcher = request.getRequestDispatcher("/ProductControl?action=Anal-Year-Month-Day");
                dispatcher.forward(request, response);
                break;
            case "orders_Anal-Years-Months":
                dispatcher = request.getRequestDispatcher("/ProductControl?action=Anal-Years-Months");
                dispatcher.forward(request, response);
                break;
            case "orders":
                dispatcher = request.getRequestDispatcher("/OrdersControl?action=");
                dispatcher.forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doGet(request, response);
    }

}
