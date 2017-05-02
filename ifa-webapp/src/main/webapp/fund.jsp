<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>4analysis</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen"
          href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="http://localhost:8080/4analysis"><strong>4</strong><em>analysis</em></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <%--<li><a href="#">About</a></li>--%>
            </ul>

            <form class="navbar-form">
                <div class="form-group" style="display:inline;">
                    <div class="input-group" style="display:table;">
                        <span class="input-group-btn" style="width:10%;">
                            <button type="submit" class="btn btn-default">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </span>
                        <input class="form-control" name="search" placeholder="znajdź fundusz" autocomplete="off"
                               autofocus="autofocus" type="text" list="funds">
                        <datalist id="funds">
                            <%--<c:choose>--%>
                            <%--<c:when test="${dataFound}">--%>
                            <%--<c:forEach items="${allInvestFunds}" var="investFund">--%>
                            <%--<option value="${investFund.name}"></option>--%>
                            <%--</c:forEach>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                            <%--<option value="brak danych"></option>--%>
                            <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        </datalist>
                    </div>
                </div>
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="http://localhost:8080/4analysis/notowania/${investFund.id}">Notowania <span
                        class="sr-only">(current)</span></a></li>
                <li><a href="http://localhost:8080/4analysis/analiza/${investFund.id}">Analiza techniczna</a></li>
            </ul>
        </div>
        <div class="col-md-8 col-md-offset-2 main">
            <div class="row">
                <h3 class="page-header"><a
                        href="http://localhost:8080/4analysis/notowania/${investFund.id}">${investFund.name}</a></h3>
                <span class="text-muted">${investFund.id} </span><br>
                <div class="col-md-4">
                    <span class="lead"><strong>${investFund.date} PLN </strong></span>
                    <c:choose>
                        <c:when test="${investFund.diff > 0}">
                            <span class="glyphicon glyphicon-arrow-up green"></span>
                            <span class="green"> +${investFund.diff}% </span>
                        </c:when>
                        <c:when test="${investFund.diff < 0}">
                            <span class="glyphicon glyphicon-arrow-down red"></span>
                            <span class="red"> ${investFund.diff}% </span>
                        </c:when>
                        <c:otherwise>
                            <span class="glyphicon glyphicon-arrow-right grey"></span>
                            <span class="grey"> 0.00% </span>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row">
                <span class="text-info">wycena na dzień ${investFund.date}</span>
            </div>

            <div class="row main">
                <h4 class="page-header">Notowania</h4>
                <form method="get" action="/4analysis/notowania/${investFund.id}">
                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-3 box">
                                <label>od</label>
                                <div class="input-group">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                                    <input type="date" name="from" value="${from}" class="form-control"
                                           placeholder="od">
                                </div>
                            </div>
                            <div class="col-xs-3 col-md-offset-1 box">
                                <label>do</label>
                                <div class="input-group">
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                                    <input type="date" name="to" value="${to}" class="form-control" placeholder="od">
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-info">Pokaż notowania</button>
                </form>

                <div class="table-responsive col-md-4">
                    <table class="table table-striped table-condensed table-hover-other">
                        <thead>
                        <tr>
                            <th>data</th>
                            <th>wycena</th>
                        </tr>
                        </thead>
                        <tbody class="table-other">
                        <c:forEach items="${ratings}" var="rating">
                            <tr>
                                <td>${rating.date}</td>
                                <td>${rating.close}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>