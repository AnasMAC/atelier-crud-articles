<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Edit Article</title>
    <style>
        .form-container { width: 350px; margin: 40px auto; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: bold; }
        .form-group input { width: 100%; padding: 8px; box-sizing: border-box; }
        .btn-submit { width: 100%; padding: 10px; background-color: #2196F3; color: white; border: none; cursor: pointer; margin-top: 10px; }
        .btn-submit:hover { background-color: #0b7dda; }
        .msg-error { color: red; text-align: center; font-weight: bold; margin-bottom: 10px; }
        .back-link { display: block; text-align: center; margin-top: 15px; }
    </style>
</head>
<body>

<h2 style="text-align: center;">Edit Article</h2>

<c:if test="${not empty error}">
    <div class="msg-error">${error}</div>
</c:if>

<c:if test="${not empty Article}">
    <div class="form-container">
        <!-- Sends the updated data to the /article/update route -->
        <form action="${pageContext.request.contextPath}/article/update" method="post">

            <div class="form-group">
                <label for="code">Code</label>
                <!-- Readonly because we don't want to change the ID/Code when updating -->
                <input type="text" id="code" name="code" value="${Article.code}" readonly>
            </div>

            <div class="form-group">
                <label for="destination">Destination</label>
                <input type="text" id="destination" name="destination" value="${Article.destination}" required>
            </div>

            <div class="form-group">
                <label for="prix">Prix</label>
                <input type="number" step="0.01" id="prix" name="prix" value="${Article.prix}" required>
            </div>

            <button type="submit" class="btn-submit">Update Article</button>
        </form>
    </div>
</c:if>

<a href="${pageContext.request.contextPath}/article/list" class="back-link">Return to List</a>

</body>
</html>