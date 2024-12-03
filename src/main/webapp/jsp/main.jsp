<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main</title>
    <link href="https://unpkg.com/@picocss/pico@latest/css/pico.min.css" rel="stylesheet">
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main class="container">
    <%--<section>
        <p>Hello, <strong>${user}</strong>!</p>
        <p>Session for: <strong>${user_name}</strong></p>
        <p>Current page: <strong>${current_page}</strong></p>
    </section>--%>

    <section>
        <h2>Alien Creatures</h2>
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Lor</th>
                <th>Image</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${aliensList}" var="alien">
                <tr>
                    <td>${alien.name}</td>
                    <td>${alien.lor}</td>
                    <td>
                        <c:if test="${not empty alien.base64Image}">
                            <img src="data:image/jpeg;base64,${alien.base64Image}" alt="Character Image" style="max-width: 100px; height: auto;"/>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
</main>
</body>
</html>