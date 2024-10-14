<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
    <h1>Error Page</h1>
    <p><b>Error Details:</b></p>
    <ul>
        <li>Request from ${pageContext.errorData.requestURI} is failed</li>
        <li>Status code: ${pageContext.errorData.statusCode}</li>
        <li>Servlet name: ${pageContext.errorData.servletName}</li>
        <li>Exception: ${pageContext.exception}</li>
        <li>Message from exception: ${pageContext.exception.message}</li>
    </ul>
    <a href="${pageContext.request.contextPath}">Go to Home</a>
</body>
</html>
