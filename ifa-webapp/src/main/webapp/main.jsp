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
    <link href="/css/dataTables.bootstrap.css" rel="stylesheet">
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-8 col-md-offset-2 main">
            <div class="panel-heading"><strong>Polecane</strong></div>
            <div class="table-responsive">
                <table table id="table-promoted" class="table table-striped table-hover-promo compact" style="display: none">
                    <thead>
                    <tr>
                        <th class="text-center col-md-1 sorting_disabled" style="width: 10%">
                            <span class="glyphicon glyphicon-tags"></span>
                        </th>
                        <th style="width: 40%">fundusz</th>
                        <th class="text-center" style="width: 10%">id</th>
                        <th class="text-center" style="width: 15%" >data</th>
                        <th class="text-right" style="width: 15%">wartość j.u. netto <em>PLN</em></th>
                        <th>zmiana</th>
                    </tr>
                    </thead>
                    <tbody class="table-promo">
                    <c:choose>
                        <c:when test="${dataFound}">
                            <c:forEach items="${promotedInvestFunds}" var="investFund">
                                <tr onclick="window.document.location='/4analysis/notowania/${investFund.id}';">
                                    <td class="text-center promo-color">
                                        <c:choose>
                                            <c:when test="${investFund.priority > 66}">
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star"></span>
                                                <span class="glyphicon glyphicon-star"></span>
                                            </c:when>
                                            <c:when test="${investFund.priority > 33}">
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
                                    <td class="text-center">${investFund.date}</td>
                                    <td class="text-right">${investFund.close}</td>
                                    <c:choose>
                                        <c:when test="${investFund.diff > 0}">
                                            <td class="text-left green">
                                                <span class="glyphicon glyphicon-arrow-up green"></span>
                                                +${investFund.diff}%
                                            </td>
                                        </c:when>
                                        <c:when test="${investFund.diff < 0}">
                                            <td class="text-left red">
                                                <span class="glyphicon glyphicon-arrow-down red"></span> ${investFund.diff}%
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="text-left grey">
                                                <span class="glyphicon glyphicon-arrow-right grey"></span> 0.00%
                                            </td>
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
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>

            <div class="panel-heading">Pozostałe</div>
            <div class="table-responsive">
                <table id="table-not-promoted" class="table table-striped table-hover-other compact" style="display: none">
                    <thead>
                    <tr>
                        <th class="text-center col-md-1 sorting_disabled" style="width: 10%">
                            <span class="glyphicon glyphicon-tags"></span>
                        </th>
                        <th style="width: 40%">fundusz</th>
                        <th class="text-center" style="width: 10%">id</th>
                        <th class="text-center" style="width: 15%" >data</th>
                        <th class="text-right" style="width: 15%">wartość j.u. netto <em>PLN</em></th>
                        <th>zmiana</th>
                    </tr>
                    </thead>
                    <tbody class="table-other">
                    <c:choose>
                        <c:when test="${dataFound}">
                            <c:forEach items="${notPromotedInvestFunds}" var="investFund">
                                <tr onclick="window.document.location='/4analysis/notowania/${investFund.id}';">
                                    <td class="text-center"></td>
                                    <td>${investFund.name}</td>
                                    <td class="text-center">${investFund.id}</td>
                                    <td class="text-center">${investFund.date}</td>
                                    <td class="text-right">${investFund.close}</td>
                                    <c:choose>
                                        <c:when test="${investFund.diff > 0}">
                                            <td class="text-left green">
                                                <span class="glyphicon glyphicon-arrow-up green"></span>
                                                +${investFund.diff}%
                                            </td>
                                        </c:when>
                                        <c:when test="${investFund.diff < 0}">
                                            <td class="text-left red">
                                                <span class="glyphicon glyphicon-arrow-down red"></span> ${investFund.diff}%
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="text-left grey">
                                                <span class="glyphicon glyphicon-arrow-right grey"></span> 0.00%
                                            </td>
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
                            </tr>
                        </c:otherwise>
                    </c:choose>
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

        var datatable = $('.table').DataTable({
            "responsive": true,
            "aoColumnDefs": [
                {"bSortable": false, "aTargets": [0]}
            ]
        });

        $(".table").show();

        datatable.draw().columns.adjust().responsive.recalc();
    });
</script>
</body>
</html>