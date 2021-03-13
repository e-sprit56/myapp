<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: konrad
  Date: 06.03.2021
  Time: 14:10
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
            <th>Nazwa</th>
            <th>Typ</th>
            <th>Właściciel</th>
            <th>Opcje</th>
        </tr>
        <c:forEach items="${properties}" var="property">
            <tr>
                <td>${property.shortName}</td>
                <td>${property.type}</td>
                <td>${property.owner.username}</td>
                <td> <form method="post" action="/app/rateschema/addschema-components">
                        <input type="hidden" name="propertyId" value="${property.id}">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <button type="submit">Dodaj Schemat Rozliczeń</button>
                    </form>
                </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <br>
    <form method="get" action="/app/properties/add-property">
        <button type="submit">Dodaj nieruchomość</button>
    </form>
</div>

</body>
</html>
