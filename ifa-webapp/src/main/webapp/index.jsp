<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Moje tłumaczenia</title>
</head>
<body>

<%--<form method="get" action="/investfunds">--%>
    <%--<input type="text" name="word" value="${word}"> <input type="submit">--%>
<%--</form>--%>

<div>
    <c:forEach items="${investFunds}" var="if">
        <li> ${if}</li>
    </c:forEach>
</div>

</body>
</html>
