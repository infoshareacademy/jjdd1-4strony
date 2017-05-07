<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>4analysis</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">
    <link href="/css/updatefund.css" rel="stylesheet">
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container-fluid main">
    <form class="form-horizontal" role="form" action="/4analysis/updatefund" method="post">
        <h2>Zmień dane funduszu</h2>
        <div class="form-group">
            <label for="fund-id" class="col-sm-4 control-label">wybierz wg ID</label>
            <div class="col-sm-4">
                <select name="id" id="fund-id" class="form-control">
                    <option>-</option>
                    <c:forEach items="${investFunds}" var="investFund">
                        <option value="${investFund.id}">${investFund.id}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="fund-name" class="col-sm-4 control-label">nazwa</label>
            <div class="col-sm-4">
                <input id="fund-name" type=text" name="name" placeholder="pełna nazwa funduszu" class="form-control"
                       required>
            </div>
        </div>
        <div class="form-group">
            <label for="fund-priority" class="col-sm-4 control-label">promocja</label>
            <div class="col-sm-4">
                <input id="fund-priority" type="number" name="priority" min="0" max="100"
                       placeholder="liczby od 0 do 100"
                       class="form-control" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-4 col-sm-offset-4">
                <button type="submit" class="btn btn-primary btn-block">Uaktualnij bazę danych</button>
            </div>
        </div>
    </form>
</div>
<%@include file="footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>
<script src="/js/updatefund-ajax.js"></script>
</body>
</html>
