<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: konrad
  Date: 05.03.2021
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<jsp:include page="../components/app-head.jsp"/>
<body>
<jsp:include page="../components/sidenav.jsp"/>

<div class="main">
<form:form method="post" modelAttribute="property">

    Nazwa nieruchomości:<br>
    <form:input path="shortName"/><br>
    <form:errors path="shortName"/><br>
    Miasto: <br>
    <form:input path="city"/><br>
    <form:errors path="city"/><br>
    Kod pocztowy: <br>
    <form:input path="postCode"/><br>
    <form:errors path="postCode"/><br>
    Ulica i numer: <br>
    <form:input path="streetAndNumber"/><br>
    <form:errors path="streetAndNumber"/><br>
    Typ: <br>
    <form:select path="type">
    <form:option value="0">--Wybierz--</form:option>
    <form:options items="${types}"></form:options>
    </form:select>

    <input type="submit" value="Dodaj nieruchomośc"/>
</form:form>
</div>div>

</body>
</html>
