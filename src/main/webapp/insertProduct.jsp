<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert Products</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body class="bg-light">
<a href="index.jsp">Home</a>
<div class="container mt-5">
    <h1>Insert Products</h1>
    <form action="controls?action=insert_products" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" name="name" required/>
        </div>
        <div class="form-group">
            <label for="desc">Description:</label>
            <input type="text" class="form-control" name="desc" required/>
        </div>
        <div class="form-group">
            <label for="unit">Unit:</label>
            <input type="text" class="form-control" name="unit" required/>
        </div>
        <div class="form-group">
            <label for="manu">Manufacturer:</label>
            <input type="text" class="form-control" name="manu" required/>
        </div>
        <div class="form-group">
            <label for="status">Status:</label>
            <select class="form-control" name="status">
                <option selected="true" value="ACTIVE">ACTIVE</option>
                <option value="IN_ACTIVE">IN_ACTIVE</option>
                <option value="TERMINATED">TERMINATED</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Insert</button>
        <button type="reset" class="btn btn-secondary">Clear</button>
    </form>
</div>

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
