<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: konrad
  Date: 21.03.2021
  Time: 09:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../components/app-head.jsp"/>
<body>
<jsp:include page="../components/sidenav.jsp"/>

<div class="main">

    <form:form modelAttribute="calculation">

        <h4>Okres: ${calculation.period}</h4>

        Najem: ${calculation.rent.fixedCost}<br>

        Opłaty administracyjne: ${calculation.administration.fixedCost}<br>

        Woda: ${calculation.water.totalCost}<br>
        opłaty stałe: ${calculation.water.fixedCost}<br>
        opłaty zmienne: ${calculation.water.variableCost}<br>
        Gaz: ${calculation.gas.totalCost}<br>
        opłaty stałe: ${calculation.gas.fixedCost}<br>
        opłaty zmienne: ${calculation.gas.variableCost}<br>
        Prąd: ${calculation.electricity.totalCost}<br>
        opłaty stałe: ${calculation.electricity.fixedCost}<br>
        opłaty zmienne: ${calculation.electricity.variableCost}<br>
        Telewizja: ${calculation.tv.totalCost}<br>
        Internet: ${calculation.broadband.totalCost}<br>

    </form:form>


</div>

</body>
</html>
