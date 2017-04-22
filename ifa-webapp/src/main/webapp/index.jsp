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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/dashboard.css" rel="stylesheet">
    <style>
        .table-other {
            background-color: #e4e6ea;
        }

        .table-promo {
            background-color: #f7eaef;
        }

        .table-hover-promo tbody tr:hover td, .table-hover tbody tr:hover th {
            background-color: #d99bb2;
        }

        .table-hover-other tbody tr:hover td, .table-hover tbody tr:hover th {
            background-color: #a7adba;
        }

        .green {
            color: green;
        }

        .red {
            color: red;
        }

        .grey {
            color: grey;
        }

        .promo-color {
            color: #e12453;
        }
        /*.navbar-promo h4:hover {*/
        /*background-color: #d2e8ae;*/
        /*}*/
    </style>
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
            <a class="navbar-brand" href="http://localhost:8080/investfunds">Invest Funds Analysis</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <%--<ul class="nav navbar-nav navbar-right">--%>
            <%--<li><a href="#">Dashboard</a></li>--%>
            <%--<li><a href="#">Settings</a></li>--%>
            <%--<li><a href="#">Profile</a></li>--%>
            <%--<li><a href="#">Help</a></li>--%>
            <%--</ul>--%>
            <%--<form class="navbar-form navbar-right">--%>
            <%--<input type="text" class="form-control" placeholder="Search...">--%>
            <%--</form>--%>
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

            <%--<h2 class="page-header"></h2>--%>
            <%--<div class="row placeholders">--%>
            <%--<c:forEach items="${promotedInvestFunds}" var="promotedInvestFund">--%>
            <%--<div class="col-xs-6 col-sm-3 placeholder navbar-promo">--%>
            <%--&lt;%&ndash;<img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="&ndash;%&gt;--%>
            <%--&lt;%&ndash;width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">&ndash;%&gt;--%>
            <%--<h4>${promotedInvestFund.name}</h4>--%>
            <%--<span class="text-muted">${promotedInvestFund.id}</span><br>--%>
            <%--<span class="text-info">kurs na dzień ${promotedInvestFund.currentRatingDate}:</span><br>--%>
            <%--<span class="text-success">${promotedInvestFund.currentRatingValue} PLN</span>--%>
            <%--</div>--%>
            <%--</c:forEach>--%>
            <%--</div>--%>

            <h2 class="page-header">Polecane</h2>
            <div class="table-responsive">
                <table class="table table-striped table-hover-promo">
                    <thead>
                    <tr>
                        <th>
                            <span class="glyphicon glyphicon-tags"/>
                        </th>
                        <th>fundusz</th>
                        <th>id</th>
                        <th>data</th>
                        <th></th>
                        <th>wartość j.u. netto w PLN</th>
                    </tr>
                    </thead>
                    <tbody class="table-promo">
                    <c:forEach items="${promotedInvestFunds}" var="investFund">
                        <tr>
                            <td class="promo-color">
                                <c:choose>
                                    <c:when test="${investFund.priority < -66}">
                                        <span class="glyphicon glyphicon-star"/>
                                        <span class="glyphicon glyphicon-star"/>
                                        <span class="glyphicon glyphicon-star"/>
                                    </c:when>
                                    <c:when test="${investFund.priority < -33}">
                                        <span class="glyphicon glyphicon-star"/>
                                        <span class="glyphicon glyphicon-star"/>
                                        <span class="glyphicon glyphicon-star-empty"/>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="glyphicon glyphicon-star"/>
                                        <span class="glyphicon glyphicon-star-empty"/>
                                        <span class="glyphicon glyphicon-star-empty"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${investFund.name}</td>
                            <td>${investFund.id}</td>
                            <td>${investFund.currentRatingDate}</td>
                            <td>
                            <c:choose>
                                <c:when test="${investFund.state > 0}">
                                    <span class="glyphicon glyphicon-arrow-up green"/>
                                </c:when>
                                <c:when test="${investFund.state == 0}">
                                    <span class="glyphicon glyphicon-arrow-right grey"/>
                                </c:when>
                                <c:when test="${investFund.state < 0}">
                                    <span class="glyphicon glyphicon-arrow-down red"/>
                                </c:when>
                            </c:choose>
                            </td>
                            <td>${investFund.currentRatingValue}</td>
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
                        <th>
                            <span class="glyphicon glyphicon-tags"/>
                        </th>
                        <th>fundusz</th>
                        <th>id</th>
                        <th>data</th>
                        <th></th>
                        <th>wartość j.u. netto w PLN</th>
                    </tr>
                    </thead>
                    <tbody class="table-other">
                    <c:forEach items="${otherInvestFunds}" var="investFund">
                        <tr>
                            <td></td>
                            <td>${investFund.name}</td>
                            <td>${investFund.id}</td>
                            <td>${investFund.currentRatingDate}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${investFund.state > 0}">
                                        <span class="glyphicon glyphicon-arrow-up green"/>
                                    </c:when>
                                    <c:when test="${investFund.state == 0}">
                                        <span class="glyphicon glyphicon-arrow-right grey"/>
                                    </c:when>
                                    <c:when test="${investFund.state < 0}">
                                        <span class="glyphicon glyphicon-arrow-down red"/>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>${investFund.currentRatingValue}</td>
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