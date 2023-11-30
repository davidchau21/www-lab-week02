<%@ page import="vn.edu.iuh.fit.backend.services.ProductServices" %>
<%@ page import="vn.edu.iuh.fit.backend.models.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products Listing</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            padding: 20px;
        }
        table {
            width: 70%;
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
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<%
    ProductServices productServices = new ProductServices();
    List<Product> lst = productServices.getAll();
%>
<a href="index.jsp">Home</a>
<h2>Product</h2>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Unit</th>
        <th>Manufacturer</th>
        <th>Status</th>
        <th colspan="2"><a href="insertProduct.jsp">Insert</a></th>
    </tr>
    </thead>
    <tbody>
    <% for (Product product : lst) {
        long id = product.getId();
        String deleteString = "controls?action=delete_product&id=" + id;
        String editString = "controls?action=edit_product&id=" + id;
    %>
    <tr>
        <td><%= id %></td>
        <td><%= product.getName() %></td>
        <td><%= product.getDescription() %></td>
        <td><%= product.getUnit() %></td>
        <td><%= product.getManufacturer() %></td>
        <td><%= product.getStatus() %></td>
        <td><a href="<%= editString %>">Update</a></td>
        <td><a href="<%= deleteString %>">Delete</a></td>
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
