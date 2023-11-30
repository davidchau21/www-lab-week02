<%@ page import="vn.edu.iuh.fit.backend.services.CustomerServices" %>
<%@ page import="vn.edu.iuh.fit.backend.models.Customer" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            padding: 20px;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #dee2e6;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        a {
            color: #007bff;
            text-decoration: none;
            margin-right: 10px;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<%
    CustomerServices services = new CustomerServices();
    List<Customer> lst = services.getAll();
%>
<a href="index.jsp">Home</a>
<h2>Customer</h2>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Email</th>
        <th colspan="2"><a href="insertCustomer.jsp">Insert</a></th>
    </tr>
    </thead>
    <tbody>
    <% if (lst != null && !lst.isEmpty()) { %>
    <% for(Customer customer: lst) {
        long id = customer.getId();
        String deleteString = "controls?action=delete_cust&id=" + id;
        String editString = "controls?action=edit_cust&id=" + id;
    %>
    <tr>
        <td><%= id%></td>
        <td><%= customer.getName() %></td>
        <td><%= customer.getAddress() %></td>
        <td><%= customer.getPhone() %></td>
        <td><%= customer.getEmail() %></td>
        <td><a href="<%= editString %>">Update</a></td>
        <td><a href="<%= deleteString %>">Delete</a></td>
    </tr>
    <% } %>
    <% } else { %>
    <tr>
        <td colspan="6">No customers found.</td>
    </tr>
    <% } %>
    </tbody>
</table>

<!-- Bootstrap JS and Popper.js -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
