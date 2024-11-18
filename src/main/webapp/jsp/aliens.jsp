<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Alien Characters</title>
</head>
<body>

<h1>Add New Alien Character</h1>

<form method="POST" action="${pageContext.request.contextPath}/controller" enctype="multipart/form-data">
    <input type="hidden" name="command" value="add_alien"/>
    Name:<br/>
    <input type="text" name="name" value="" required /><br/>
    Lor:<br/>
    <input type="text" name="lor" value="" required /><br/>
    Choose Image:<br/>
    <input type="file" name="file" /><br/>
    ${errorPassMessage} <br/>
    <input type="submit" value="Add Character"/>
    <br/>
    <a href="controller?command=logout">Logout</a>
</form>

<h2>Alien Creatures</h2>
<ul>
    <c:forEach items="${aliensList}" var="alien">
        <li>
            Name: ${alien.name}, Lor: ${alien.lor}
            <c:if test="${not empty alien.base64Image}">
                Image: <img src="data:image/jpeg;base64,${alien.base64Image}" alt="Character Image" />
            </c:if>
        </li>
    </c:forEach>
</ul>

</body>
</html>