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
    <link href="/css/promotions.css" rel="stylesheet">
    <link href="/css/dataTables.bootstrap.css" rel="stylesheet">
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container-fluid">
    <div class="row">

        <div class="col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="<c:url value = "/4analysis/promocje"/>">Promocja funduszy<span
                        class="sr-only">(current)</span></a></li>
                <li><a href="<c:url value = "/4analysis/raporty/popularnosc"/>">Raport popularności</a></li>
                <li>
                    <a href="<c:url value = "/4analysis/raporty/zigzag"/>">Raport wartości Zig-Zag
                    </a>
                </li>
            </ul>
        </div>

        <div class="col-md-10 col-md-offset-2 main">
            <form class="form-horizontal" role="form" action="/4analysis/promocje" method="post">
                <h4>Promocja funduszy</h4>
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
                        <input id="fund-name" type=text" name="name" placeholder="pełna nazwa funduszu"
                               class="form-control"
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

            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="table-responsive">
                        <table table id="table-promoted" class="table table-striped table-hover-promo compact"
                               style="display: none">
                            <thead>
                            <tr>
                                <th style="width: 40%">fundusz</th>
                                <th class="text-center" style="width: 10%">id</th>
                                <th class="text-right" style="width: 15%">priorytet</th>
                            </tr>
                            </thead>
                            <tbody class="table-promo">
                            <c:forEach items="${promotedInvestFunds}" var="investFund">
                                <tr onclick="window.document.location='/4analysis/notowania/${investFund.id}';">
                                    <td>${investFund.name}</td>
                                    <td class="text-center">${investFund.id}</td>
                                    <td class="text-right">${investFund.priority}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/promotions.js"></script>
<script src="/js/jquery.dataTables.min.js"></script>
<script src="/js/dataTables.bootstrap.min.js"></script>
<script>
    $(document).ready(function () {

        var datatable = $('.table').DataTable({
            "responsive": true,
            "aoColumnDefs": [
                {"bSortable": false, "aTargets": [0]}
            ],
            "bPaginate": false,
            "bLengthChange": false
        });

        $(".table").show();

        datatable.draw().columns.adjust().responsive.recalc();
    });
</script>
</body>
</html>
