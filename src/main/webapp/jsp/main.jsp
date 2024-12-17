<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.userLocale}" />
<fmt:setBundle basename="locale.messages"/>
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
        .add-btn {
            background-color: #28a745;
            color: #fff;
            padding: 0.5rem 1rem;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
        }
        .add-btn:hover {
            background-color: #218838;
        }
        .error-banner {
            background-color: #f44336; /* Red for error */
            color: white;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
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
        <c:if test="${isSuccessMessage == true}">
            <div class="success-message">
                <fmt:message key="delete_alien" />
            </div>
        </c:if>
        <c:if test="${sessionScope.is_active == true}">
            <a class="add-btn" href="${pageContext.request.contextPath}/jsp/add_alien.jsp">
                Add New Alien
            </a>
        </c:if>
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Lor</th>
                <th>Image</th>
                <th>Actions</th>
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
                    <td>
                        <c:if test="${alien.userId == sessionScope.currentUserId || sessionScope.userRole == 'ADMIN' || sessionScope.userRole == 'MODER'}">
                            <a href="${pageContext.request.contextPath}/controller?command=DELETE_ALIEN&alienId=${alien.id}"
                               style="background-color: red; color: white; padding: 5px; text-decoration: none; border-radius: 3px;">
                                Delete
                            </a>
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