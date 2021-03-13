<%--
  Created by IntelliJ IDEA.
  User: konrad
  Date: 01.03.2021
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyApp Login Page</title>
</head>
<body>

<form method="post">
    <div>
        <label>Użytkownik :</label><br>
            <input type="text" name="username"/>
    </div>
    <div>
        <label>Hasło :</label><br>
            <input type="password" name="password"/>
    </div>
    <div>
        <input type="submit" value="Zaloguj"/>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>

Jeśli nie masz konta zarejestruj się:

<a href="/registration"><button>Zarejestruj się</button></a>



</body>
</html>
