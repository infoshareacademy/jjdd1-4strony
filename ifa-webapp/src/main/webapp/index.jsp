<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Invest Funds Analisys</title>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
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
            <li><a href="#">About</a></li>
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
                            <c:forEach items="${allInvestFunds}" var="investFund">
                                <option value="${investFund.name}"></option>
                            </c:forEach>
                        </datalist>
                    </div>
                </div>
            </form>
        </div>
    </div>
</nav>

<%--<form method="get" action="/investfunds">--%>
<%--<input type="text" name="word" value="${word}"> <input type="submit">--%>
<%--</form>--%>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-8 col-md-offset-2 main">
            <h1 class="page-header">Notowania funduszy inwestycyjnych</h1>

            <h2 class="page-header">Polecane</h2>
            <div class="table-responsive">
                <table class="table table-striped table-hover-promo">
                    <thead>
                    <tr>
                        <th class="text-center col-md-1">
                            <span class="glyphicon glyphicon-tags"></span>
                        </th>
                        <th>fundusz</th>
                        <th class="text-center">id</th>
                        <th class="text-center">data</th>
                        <th class="text-right">wartość j.u. netto <em>PLN</em></th>
                        <th></th>
                        <th>zmiana</th>
                    </tr>
                    </thead>
                    <tbody class="table-promo">
                    <c:forEach items="${promotedInvestFunds}" var="investFund">
                        <tr>
                            <td class="text-center promo-color">
                                <c:choose>
                                    <c:when test="${investFund.priority < -66}">
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star"></span>
                                    </c:when>
                                    <c:when test="${investFund.priority < -33}">
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="glyphicon glyphicon-star"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${investFund.name}</td>
                            <td class="text-center">${investFund.id}</td>
                            <td class="text-center">${investFund.currentRatingDate}</td>
                            <td class="text-right">${investFund.currentRatingValue}</td>
                                <c:choose>
                                    <c:when test="${investFund.change > 0}">
                                        <td class="text-left">
                                        <span class="glyphicon glyphicon-arrow-up green"></span>
                                        </td>
                                        <td class="text-left green"> +${investFund.change}%</td>
                                    </c:when>
                                    <c:when test="${investFund.change < 0}">
                                        <td class="text-left">
                                        <span class="glyphicon glyphicon-arrow-down red"></span>
                                        </td>
                                        <td class="text-left red"> ${investFund.change}%</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="text-left">
                                        <span class="glyphicon glyphicon-arrow-right grey"></span>
                                        </td>
                                        <td class="text-left grey"> - </td>
                                    </c:otherwise>
                                </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <h3 class="sub-header">Pozostałe</h3>
            <%--<div class="panel-heading">Panel heading</div>--%>
            <div class="table-responsive">
                <table class="table table-striped table-hover-other">
                    <thead>
                    <tr>
                        <th class="text-center col-md-1">
                            <span class="glyphicon glyphicon-tags"></span>
                        </th>
                        <th>fundusz</th>
                        <th class="text-center">id</th>
                        <th class="text-center">data</th>
                        <th class="text-right">wartość j.u. netto <em>PLN</em></th>
                        <th></th>
                        <th>zmiana</th>
                    </tr>
                    </thead>
                    <tbody class="table-other">
                    <c:forEach items="${otherInvestFunds}" var="investFund">
                        <tr>
                            <td class="text-center"></td>
                            <td>${investFund.name}</td>
                            <td class="text-center">${investFund.id}</td>
                            <td class="text-center">${investFund.currentRatingDate}</td>
                            <td class="text-right">${investFund.currentRatingValue}</td>
                                <c:choose>
                                    <c:when test="${investFund.change > 0}">
                                        <td class="text-left">
                                            <span class="glyphicon glyphicon-arrow-up green"></span>
                                        </td>
                                        <td class="text-left green"> +${investFund.change}%</td>
                                    </c:when>
                                    <c:when test="${investFund.change < 0}">
                                        <td class="text-left">
                                        <span class="glyphicon glyphicon-arrow-down red"></span>
                                        </td>
                                        <td class="text-left red"> ${investFund.change}%</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="text-left">
                                        <span class="glyphicon glyphicon-arrow-right grey"></span>
                                        </td>
                                        <td class="text-left grey"> - </td>
                                    </c:otherwise>
                                </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>