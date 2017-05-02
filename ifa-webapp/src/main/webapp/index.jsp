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
    <link href="/css/styles.css" rel="stylesheet">
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/dataTables.bootstrap.min.css" rel="stylesheet">
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
                    <c:choose>
                        <c:when test="${dataFound}">
                            <c:forEach items="${promotedInvestFunds}" var="investFund">
                                <tr onclick="window.document.location='/4analysis/notowania/${investFund[1]}';">
                                    <td class="text-center promo-color">
                                        <c:choose>
                                            <c:when test="${investFund[2] > 66}">
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star"></span>
                                            </c:when>
                                            <c:when test="${investFund[2] > 33}">
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
                                    <td>${investFund[0]}</td>
                                    <td class="text-center">${investFund[1]}</td>
                                    <td class="text-center">${investFund[3]}</td>
                                    <td class="text-right">${investFund[4]}</td>
                                    <c:choose>
                                        <c:when test="${investFund[5] > 0}">
                                            <td class="text-left">
                                                <span class="glyphicon glyphicon-arrow-up green"></span>
                                            </td>
                                            <td class="text-left green"> +${investFund[5]}%</td>
                                        </c:when>
                                        <c:when test="${investFund[5] < 0}">
                                            <td class="text-left">
                                                <span class="glyphicon glyphicon-arrow-down red"></span>
                                            </td>
                                            <td class="text-left red"> ${investFund[5]}%</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="text-left">
                                                <span class="glyphicon glyphicon-arrow-right grey"></span>
                                            </td>
                                            <td class="text-left grey"> 0.00%</td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td></td>
                                <td>brak danych</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
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
                    <c:choose>
                        <c:when test="${dataFound}">
                            <c:forEach items="${otherInvestFunds}" var="investFund">
                                <tr onclick="window.document.location='/4analysis/notowania/${investFund[1]}';">
                                    <td class="text-center"></td>
                                    <td>${investFund[0]}</td>
                                    <td class="text-center">${investFund[1]}</td>
                                    <td class="text-center">${investFund[3]}</td>
                                    <td class="text-right">${investFund[4]}</td>
                                    <c:choose>
                                        <c:when test="${investFund[5] > 0}">
                                            <td class="text-left">
                                                <span class="glyphicon glyphicon-arrow-up green"></span>
                                            </td>
                                            <td class="text-left green"> +${investFund[5]}%</td>
                                        </c:when>
                                        <c:when test="${investFund[5] < 0}">
                                            <td class="text-left">
                                                <span class="glyphicon glyphicon-arrow-down red"></span>
                                            </td>
                                            <td class="text-left red"> ${investFund[5]}%</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="text-left">
                                                <span class="glyphicon glyphicon-arrow-right grey"></span>
                                            </td>
                                            <td class="text-left grey"> 0.00%</td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td></td>
                                <td>brak danych</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="/js/jquery-3.2.1.js"></script>
<script src="/js/jquery.dataTables.min.js"></script>
<script src="/js/dataTables.bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $(".table").DataTable();
    });
</script>
</body>
</html>