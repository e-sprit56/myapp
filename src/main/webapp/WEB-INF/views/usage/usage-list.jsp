<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: konrad
  Date: 20.03.2021
  Time: 09:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../components/app-head.jsp"/>
<body>
<jsp:include page="../components/sidenav.jsp"/>

<div class="main">

    <table>
        <tr>
            <th>Rok</th>
            <th>Miesiac</th>
            <th>Woda</th>
            <th>Prąd</th>
            <th>Gaz</th>
            <th>akcje</th>
        </tr>
        <c:forEach items="${usageDTOList}" var="usage">
            <tr>
                <td>${usage.year}</td>
                <td>${usage.month}</td>
                <td>${usage.waterUsage} ${usage.waterUsageSymbol}</td>
                <td>${usage.electricityUsage} ${usage.electricityUsageSymbol}</td>
                <td>${usage.gasUsage} ${usage.gasUsageSymbol}</td>
                <td>
                    </form>
                    <form method="get" action="/app/calculation/create-calculation/${usage.id}">
                        <button type="submit">stany liczników</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

</body>
</html>
