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
    <link href="/css/dataTables.bootstrap.css" rel="stylesheet">
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-8 col-md-offset-2 main">
            <div class="table-responsive">
                <table id="table-pension-funds" class="table table-striped table-hover-other compact"
                       style="display: none">
                    <thead>
                    <tr>
                        <th style="width: 40%">fundusz</th>
                        <th class="text-center" style="width: 10%">id</th>
                        <th class="text-center" style="width: 15%">data</th>
                        <th class="text-right" style="width: 15%">wartość j.u. netto <em>PLN</em></th>
                        <th>zmiana</th>
                    </tr>
                    </thead>
                    <tbody class="table-other">
                    <c:choose>
                        <c:when test="${dataFound}">
                            <c:forEach items="${allPensionFunds}" var="pensionFund">
                                <tr>
                                <%--<tr onclick="window.document.location='/4analysis/pensionfunds';">--%>
                                        <%--<td class="text-center"></td>--%>
                                    <td>${pensionFund.name}</td>
                                    <td class="text-center">${pensionFund.id}</td>
                                    <td class="text-center">${pensionFund.date}</td>
                                    <td class="text-center">${pensionFund.close}</td>
                                    <c:choose>
                                        <c:when test="${pensionFund.diff > 0}">
                                            <td class="text-left green">
                                                <span class="glyphicon glyphicon-arrow-up green"></span>
                                                +${pensionFund.diff}%
                                            </td>
                                        </c:when>
                                        <c:when test="${pensionFund.diff < 0}">
                                            <td class="text-left red">
                                                <span class="glyphicon glyphicon-arrow-down red"></span> ${pensionFund.diff}%
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