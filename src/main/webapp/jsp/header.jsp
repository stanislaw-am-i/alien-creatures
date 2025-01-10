<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.userLocale}" />
<fmt:setBundle basename="locale.messages"/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://unpkg.com/@picocss/pico@latest/css/pico.min.css" rel="stylesheet">
</head>
<header class="container">
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}"><fmt:message key="main_page" /></a></li>
        </ul>
        <ul>
            <form method="get" style="display:inline;">
                <select name="lang" onchange="this.form.submit()">
                    <option value="en" <c:if test="${sessionScope.userLocale.language == 'en'}">selected</c:if>>Eng</option>
                    <option value="be" <c:if test="${sessionScope.userLocale.language == 'be'}">selected</c:if>>Бел</option>
                </select>
            </form>
        </ul>
        <ul>
            <c:if test="${sessionScope.is_active == false || sessionScope.is_active == null}">
                <li><a href="jsp/login.jsp"><fmt:message key="login_page" /></a></li>
                <li><a href="jsp/signup.jsp"><fmt:message key="sign_up" /></a></li>
            </c:if>

            <c:if test="${sessionScope.is_active == true}">
                <li><a href="jsp/profile.jsp"><fmt:message key="profile" /></a></li>
                <li>
                    <form method="POST" action="${pageContext.request.contextPath}/controller" style="display: inline;">
                        <input type="hidden" name="command" value="logout" />
                        <button type="submit" class="secondary" style="all: unset; color: var(--primary); text-decoration: underline; cursor: pointer;"><fmt:message key="log_out" /></button>
                    </form>
                </li>
            </c:if>
        </ul>
    </nav>
</header>