<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <c:choose>
                    <c:when test="${sessionData.logged}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                <img src="${sessionData.user.picture}" style="height: 20px">
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><span style="padding-left: 15px; padding-right: 15px">Jesteś zalogowany jako</span></li>
                                <li><span style="padding-left: 15px; padding-right: 15px"><strong>${sessionData.user.email}</strong></span></li>
                                <li role="separator" class="divider"></li>
                                <%--<li class="dropdown-header">Nav header</li>--%>
                                <li><a href="http://localhost:8080/api/google/signout">Wyloguj się</a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a style="padding-top: 8px; padding-bottom: 8px"  href="http://localhost:8080/api/google/signin">
                                <img src="http://3.bp.blogspot.com/-Ep_sMHCGb-M/Vl9h5smpeaI/AAAAAAAACak/MQF7WdfmBgI/s640/image00.png"
                                     style="height: 34px">
                            </a>
                        </li>
                        <li>
                            <a style="padding-top: 10px; padding-bottom: 10px" href="http://localhost:8080/api/facebook/signin">
                                <img src="http://readersgazette.com/images/aaa/fblogin.png"
                                     style="height: 30px">
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>

            <form class="navbar-form">
                <div class="form-group" style="display:inline;">
                    <div class="input-group" style="display:table;">
                        <span class="input-group-btn" style="width:10%;">
                            <button type="submit" class="btn btn-default">
                                <span class="glyphicon glyphicon-search" style="padding-top: 3px; padding-bottom: 3px"></span>
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