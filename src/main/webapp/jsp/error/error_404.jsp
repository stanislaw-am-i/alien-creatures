<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404 - Page Not Found</title>
</head>
<body>
    <h1>404 - Page Not Found</h1>
    <p>Sorry, the page you are looking for does not exist.</p>
    <p><b>Error Details:</b></p>
    <ul>
        <li>Requested URI: ${pageContext.errorData.requestURI}</li>
        <li>Servlet Name: ${pageContext.errorData.servletName}</li>
        <li>Status Code: ${pageContext.errorData.statusCode}</li>
    </ul>
    <a href="${pageContext.request.contextPath}">Go to Home</a>
</body>
</html>
