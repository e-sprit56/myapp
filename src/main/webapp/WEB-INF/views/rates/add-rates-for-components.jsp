<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: konrad
  Date: 10.03.2021
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../components/app-head.jsp"/>
<body>
<jsp:include page="../components/sidenav.jsp"/>

<div class="main">

    <br>

    Podaj stawki za miesiąc: <br>

    <form:form method="post" modelAttribute="rateSchema" action="/app/rateschema/generate-rate-schema">

        <c:forEach items="${rateSchema.componentList}" var="component" varStatus="status">
            <form:hidden path="componentList[${status.index}].type"/>
            <form:input path="componentList[${status.index}].plDescription" readonly="true"/>
            <input type="text" value="Stawka stała za miesiąc:" disabled="disabled">
            <form:input path="componentList[${status.index}].fixedRate" />
            <form:errors path="componentList[${status.index}].fixedRate"/>
            <input type="text" value="Stawka zmienna :" disabled="disabled"/>
            <form:input  path="componentList[${status.index}].variableRate" />
            <form:errors path="componentList[${status.index}].variableRate"/> <br><br>
        </c:forEach>

        <input type="submit" value="Zatwierdź">

    </form:form>




</div>

</body>
</html>
