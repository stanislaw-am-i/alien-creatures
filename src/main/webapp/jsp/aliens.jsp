<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alien Characters</title>
    <link rel="stylesheet" href="https://unpkg.com/@picocss/pico@1.5.7/css/pico.min.css">
</head>
<body>
<main class="container">
    <header>
        <jsp:include page="header.jsp"/>
    </header>

    <section>
        <h2>Alien Creatures</h2>
        <ul>
            <c:forEach items="${aliensList}" var="alien">
                <li>
                    <strong>Name:</strong> ${alien.name}, <strong>Lor:</strong> ${alien.lor}
                    <c:if test="${not empty alien.base64Image}">
                        <br>
                        <img src="data:image/jpeg;base64,${alien.base64Image}" alt="Character Image" style="max-width: 150px; height: auto;"/>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </section>
</main>
</body>
</html>