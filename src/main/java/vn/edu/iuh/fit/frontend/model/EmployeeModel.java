package vn.edu.iuh.fit.frontend.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.json.*;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.backend.models.Employee;
import vn.edu.iuh.fit.backend.services.EmployeeServices;


import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeModel {
    public Object insertEmp(HttpServletRequest req, HttpServletResponse resp) throws IOException, InterruptedException {
        String name = req.getParameter("name");
        String s_dob = req.getParameter("dob");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(s_dob, formatter);

        Employee employee = new Employee(name, dob.atStartOfDay(), email, phone, address, EmployeeStatus.IN_ACTIVE);
        EmployeeServices services =new EmployeeServices();
        services.insertEmployee(employee);
        return employee;
        /*ObjectWriter out = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonEmp = out.writeValueAsString(employee);
        try(Client client= ClientBuilder.newClient()){
            WebTarget wt = client.target(URI.create("http://localhost:8080/week_02/api/employees"));

            Employee empl = wt.request()
                    .post(Entity.entity(employee, MediaType.APPLICATION_JSON), Employee.class);
            System.out.println(empl);
            System.out.println("------------------------");

            return employee;
        }*/
        /*jsonEmp ="{\n" +
                "  \"id\" : 0,\n" +
                "  \"fullname\" : \"teo\",\n" +
                "  \"dob\" : \"2000-12-09\",\n" +
                "  \"email\" : \"teo@gmail.com\",\n" +
                "  \"phone\" : \"346984386743\",\n" +
                "  \"address\" : \"asf safs a\",\n" +
                "  \"status\" : \"1\"\n" +
                "}";
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonEmp))
                .uri(URI.create("http://localhost:8080/week_02/api/employees"))
//                .setHeader("Content-Type","application/json")
//                .setHeader("Accept","application/json")
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.statusCode();*/
    }
}
