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
    <link href="/css/statistic.css" rel="stylesheet">
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li>
                    <a href="<c:url value = "/4analysis/promocje"/>">Promocja funduszy</a>
                </li>
                <li class="active">
                    <a href="<c:url value = "/4analysis/raporty/popularnosc"/>">Raport popularności
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li>
                    <a href="<c:url value = "/4analysis/raporty/zigzag"/>">Raport wartości Zig-Zag
                    </a>
                </li>
            </ul>
        </div>

        <div class="col-md-10 col-md-offset-2 main">
            <h4 class="page-header">RAPORT POPULARNOŚCI FUNDUSZY</h4>
            <div class="table-responsive">
                <table class="table table-striped table-hover-other compact">
                    <thead>
                    <tr>
                        <th class="text-center">fundusz</th>
                        <th class="text-center">id</th>
                        <th class="text-center">liczba kliknięć</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${popularities}" var="popularity">
                        <tr>
                            <td class="text-center">${popularity.instrumentName}</td>
                            <td class="text-center">${popularity.instrumentId}</td>
                            <td class="text-center">${popularity.clicks}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.dataTables.min.js"></script>
<script src="/js/dataTables.bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $(".table").DataTable();
    });
</script>
</body>
</html>