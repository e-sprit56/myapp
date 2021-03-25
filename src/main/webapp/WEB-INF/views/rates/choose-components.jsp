<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: konrad
  Date: 10.03.2021
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../components/app-head.jsp"/>
<body>
<jsp:include page="../components/sidenav.jsp"/>

<div class="main">

    <h4>Wybierz element do rozliczeń</h4><br>

<form method=post action="/app/rateschema/add-rates-for-components">

   <input type="hidden" value="<c:out value="${propertyId}"/>" name="propertyId"/>


<c:forEach items="${components}" var="component">

    ${component}<input type="checkbox" value="${component}" name="components"><br><br>

</c:forEach>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

<button type="submit">Zatwierdź</button>

</form>
</div>

</body>
</html>
