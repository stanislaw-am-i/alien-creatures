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
    <style>
        .active {
            font-weight: bold;
            text-decoration: underline;
            color: var(--primary);
        }
        .pagination a.active {
            background-color: #007bff;
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="header.jsp"/>
</header>
<main class="container">

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

        <nav>
            <ul class="pagination">
                <!-- Previous Link -->
                <c:if test="${currentPage > 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=${command}&page=${currentPage - 1}&pageSize=${pageSize}">
                            Previous
                        </a>
                    </li>
                </c:if>

                <!-- Page Links -->
                <c:forEach begin="1" end="${totalPages}" var="page">
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=${command}&page=${page}&pageSize=${pageSize}"
                           class="${page == currentPage ? 'active' : ''}">
                                ${page}
                        </a>
                    </li>
                </c:forEach>

                <!-- Next Link -->
                <c:if test="${currentPage < totalPages}">
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=${command}&page=${currentPage + 1}&pageSize=${pageSize}">
                            Next
                        </a>
                    </li>
                </c:if>

                <!-- Page Size Dropdown -->
                <li>
                    <form method="GET" action="${pageContext.request.contextPath}/controller" style="display: inline;">
                        <input type="hidden" name="command" value="${command}" />
                        <input type="hidden" name="page" value="${currentPage}" />
                        <label for="pageSize" style="margin-left: 10px;">Items per page:</label>
                        <select name="pageSize" id="pageSize" onchange="this.form.submit()">
                            <option value="5" ${pageSize == 5 ? 'selected' : ''}>5</option>
                            <option value="10" ${pageSize == 10 ? 'selected' : ''}>10</option>
                            <option value="20" ${pageSize == 20 ? 'selected' : ''}>20</option>
                            <option value="50" ${pageSize == 50 ? 'selected' : ''}>50</option>
                        </select>
                    </form>
                </li>
            </ul>
    </section>
</main>
</body>
</html>