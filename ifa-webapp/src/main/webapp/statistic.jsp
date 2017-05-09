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
    <link href="/css/dataTables.bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container-fluid">
    <form class="form-horizontal" role="form" action="/4analysis/statistic" method="post">
    <div class="row">
        <div class="col-md-8 col-md-offset-2 main">
            <h1 class="page-header">Statystyka notowań funduszy inwestycyjnych</h1>

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
                        <th class="text-center">liczba kliknięć</th>
                        <th></th>
                    </tr>
                    </thead>

                </table>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>
<script src="/js/jquery.dataTables.min.js"></script>
<script src="/js/dataTables.bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $(".table").DataTable();
    });
</script>
</body>
</html>