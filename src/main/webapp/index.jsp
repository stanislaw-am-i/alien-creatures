<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Alien Creatures</title>
</head>
<body>
<div>
    <div>
        <form name="send" action="controller" method="get">
            <input type="hidden" name="command" value="MULTIPLY_BY_TWO"/>
            Multiply any number by 2:<br/>
            <input type="text" name="number" value=""/>
            <input type="submit" name="submit" value="Send"/>
        </form>
    </div>
    <br/>
    <div>
        <a href="jsp/login.jsp">Go to the Login Page</a>
    </div>
</div>

</body>
</html>