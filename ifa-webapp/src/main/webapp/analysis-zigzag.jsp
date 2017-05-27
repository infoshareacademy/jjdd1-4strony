<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>4analysis</title>
    <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/img/favicon.ico" type="image/x-icon">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">
    <link href="/css/charts.css" rel="stylesheet">
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="<c:url value = "/4analysis/notowania/${investFund.id}"/>">Notowania</a></li>
                <li class="active"><a href="<c:url value = "/4analysis/analiza/zigzag/${investFund.id}"/>">Analiza
                    techniczna<span
                            class="sr-only">(current)</span></a></li>
            </ul>
        </div>
        <div class="col-md-8 col-md-offset-2 main">
            <div class="row">
                <div>
                    <h3 class="page-header">
                        <a href="<c:url value = "/4analysis/notowania/${investFund.id}"/>">${investFund.name}
                            <span style="font-size:14px" id="fund-id" class="text-muted">${investFund.id}</span>
                        </a>
                    </h3>
                </div>
                <div class="col-md-4">
                    <span class="lead"><strong>${investFund.close} PLN </strong></span>
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
            <span class="text-info">wycena na dzień ${investFund.date}</span>
            <div>
                <p>
                <ul class="nav nav-pills">
                    <li role="presentation" class="active"><a href="/4analysis/analiza/zigzag/${investFund.id}">Wskaźnik
                        Zig-Zag</a></li>
                    <li role="presentation"><a href="/4analysis/analiza/srednie/${investFund.id}">Średnie kroczące</a>
                    </li>
                </ul>
                </p>
                <span class="text-info">parametr filtrujący w [%]:</span>
                <input id="zigZag" type="number" min="0" max="100" value="${zigZag}" required><br>
            </div>
            <div id="chart-container"></div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
<script src="https://www.amcharts.com/lib/3/serial.js"></script>
<script src="https://www.amcharts.com/lib/3/themes/light.js"></script>
<script src="https://www.amcharts.com/lib/3/amstock.js"></script>
<script src="/js/zigzag-ajax.js"></script>
</body>
</html>