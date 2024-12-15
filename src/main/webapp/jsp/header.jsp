<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://unpkg.com/@picocss/pico@latest/css/pico.min.css" rel="stylesheet">
</head>
<header class="container">
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}">Home</a></li>
        </ul>
        <ul>
            <c:if test="${sessionScope.is_active == false || sessionScope.is_active == null}">
                <li><a href="jsp/login.jsp">Login</a></li>
                <li><a href="jsp/signup.jsp">Sign Up</a></li>
            </c:if>

            <c:if test="${sessionScope.is_active == true}">
                <li><a href="jsp/profile.jsp">Profile</a></li>
                <li>
                    <form method="POST" action="${pageContext.request.contextPath}/controller" style="display: inline;">
                        <input type="hidden" name="command" value="logout" />
                        <button type="submit" class="secondary" style="all: unset; color: var(--primary); text-decoration: underline; cursor: pointer;">Log Out</button>
                    </form>
                </li>
            </c:if>
        </ul>
    </nav>
</header>