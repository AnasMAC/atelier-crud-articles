<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Article List</title>
    <style>
        table { width: 80%; border-collapse: collapse; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .actions a { margin-right: 10px; text-decoration: none; color: blue; }
        .add-btn { display: block; width: 120px; margin: 20px auto; text-align: center; padding: 10px; background-color: #4CAF50; color: white; text-decoration: none; }
        .msg-success { color: green; text-align: center; font-weight: bold; margin-bottom: 10px; }
        .msg-error { color: red; text-align: center; font-weight: bold; margin-bottom: 10px; }
    </style>
</head>
<body>

<h2 style="text-align: center;">All Articles</h2>

<!-- Display delete, create, or update success messages from Session -->
<c:if test="${not empty sessionScope.message}">
    <div class="msg-success">${sessionScope.message}</div>
    <!-- Remove it so it doesn't show up again on refresh -->
    <c:remove var="message" scope="session" />
</c:if>

<!-- Display error messages from Session -->
<c:if test="${not empty sessionScope.error}">
    <div class="msg-error">${sessionScope.error}</div>
    <!-- Remove it so it doesn't show up again on refresh -->
    <c:remove var="error" scope="session" />
</c:if>

<a href="${pageContext.request.contextPath}/article/new" class="add-btn">Add New Article</a>

<table>
    <thead>
    <tr>
        <th>Code</th>
        <th>Destination</th>
        <th>Prix</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${empty articles}">
            <tr>
                <td colspan="4" style="text-align: center;">No articles found.</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="article" items="${articles}">
                <tr>
                    <td>${article.code}</td>
                    <td>${article.destination}</td>
                    <td>${article.prix}</td>
                    <td class="actions">
                        <a href="${pageContext.request.contextPath}/article/edit?code=${article.code}">Edit</a>
                        <a href="${pageContext.request.contextPath}/article/delete?code=${article.code}"
                           onclick="return confirm('Are you sure you want to delete this article?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

</body>
</html>