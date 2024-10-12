<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>"Hello World!"</h1>
<br/>
<div>
    <div>
        <form name="send" action="controller" method="get">
            <input type="text" name="number" value=""/>
            <input type="submit" name="submit" value="Send"/>
        </form>
    </div>
    <div>
        <a href="jsp/login.jsp">Login</a>
    </div>
</div>

</body>
</html>