<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <form method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="Login"/>
        Login:<br/>
        <input type="text" name="login" value=""/><br/>
        Password:<br/>
        <input type="password" name="password" value=""/><br/>
        ${errorLoginPassMessage} <br/>
        ${wrongAction} <br/>
        ${nullPage} <br/>
        <input type="submit" value="Login"/>
        </form>
    <hr/>
</body>
</html>
