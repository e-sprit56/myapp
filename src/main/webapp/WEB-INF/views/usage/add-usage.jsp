<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: konrad
  Date: 20.03.2021
  Time: 06:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../components/app-head.jsp"/>
<body>
<jsp:include page="../components/sidenav.jsp"/>

<div class="main">

    Podaj stan liczników:<br>

    <form:form method="post" modelAttribute="usageSchema" action="/app/usage/add-usage-save">

        Rok :
        <form:select path="year">
             <form:options items="${years}"></form:options>
        </form:select><br>
        Miesiąc :
        <form:select path="month">
            <form:options items="${months}"></form:options>
        </form:select><br>
        Stan liczników:
        <c:forEach items="${usageSchema.usageElementList}" var="components" varStatus="status">
            <form:input path="usageElementList[${status.index}].type" readonly="true"/>
            <form:input path="usageElementList[${status.index}].usage"/>
            <form:input path="usageElementList[${status.index}].symbol"/><br><br>
        </c:forEach>
        <form:hidden path="property"/>
        <form:hidden path="rateSchema"/>

        <input type="submit" value="Zatwierdź">

    </form:form>


</div>

</body>
</html>
