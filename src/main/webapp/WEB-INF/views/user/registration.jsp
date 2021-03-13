<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: konrad
  Date: 05.03.2021
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../components/app-head.jsp"/>
<body>
<jsp:include page="../components/sidenav.jsp"/>
<div class="main">
<form:form method="post" modelAttribute="user">

    <label>Nazwa Użytkownika:</label><br>
    <form:input path="username"/><br>
    <form:errors path="username"/><br>
    <label>Hasło: </label><br>
    <form:input path="password"/><br>
    <form:errors path="password"/>

    <input type="submit" value="Załóż konto"/>
</form:form>
</div>

</body>
</html>
