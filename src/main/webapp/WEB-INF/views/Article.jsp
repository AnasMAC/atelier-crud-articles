<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>New Article</title>
    <style>
        .form-container { width: 350px; margin: 40px auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: bold; }
        .form-group input { width: 100%; padding: 8px; box-sizing: border-box; }
        .btn-submit { width: 100%; padding: 10px; background-color: #4CAF50; color: white; border: none; cursor: pointer; margin-top: 10px; }
        .btn-submit:hover { background-color: #45a049; }
        .msg-error { color: red; text-align: center; font-weight: bold; margin-bottom: 10px; }
        .back-link { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: blue; }
    </style>
</head>
<body>

<h2 style="text-align: center;">Create New Article</h2>

<c:if test="${not empty error}">
    <div class="msg-error">${error}</div>
</c:if>

<div class="form-container">
    <!-- Sends the new data to the /article/create route -->
    <form action="${pageContext.request.contextPath}/article/create" method="post">

        <div class="form-group">
            <label for="code">Code</label>
            <!-- Unlike the Edit page, this is NOT readonly because we are creating a new one -->
            <input type="text" id="code" name="code" required>
        </div>

        <div class="form-group">
            <label for="destination">Destination</label>
            <input type="text" id="destination" name="destination" required>
        </div>

        <div class="form-group">
            <label for="prix">Prix</label>
            <input type="number" step="0.01" id="prix" name="prix" required>
        </div>

        <button type="submit" class="btn-submit">Save Article</button>
    </form>
</div>

<a href="${pageContext.request.contextPath}/article/list" class="back-link">Return to List</a>

</body>
</html>