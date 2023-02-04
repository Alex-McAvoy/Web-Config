<%--
  Created by IntelliJ IDEA.
  User: Alex McAvoy
  Date: 2022/3/6
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" isErrorPage="true" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

    <h4>Test HiddenHttpMethodFilter</h4>

    <a href="testRest/1">TestRest Get</a>
    <br><br>

    <form action="testRest" method="post">
        <input type="submit" value="TestRest POST"/>
    </form>
    <br><br>

    <form action="testRestDelete/1" method="post">
        <input type="submit" value="TestRest DELETE"/>
        <input type="hidden" name="_method" value="DELETE"/>
    </form>
    <br><br>

    <form action="testRestPut/1" method="post">
        <input type="submit" value="TestRest PUT"/>
        <input type="hidden" name="_method" value="PUT"/>
    </form>
    <br><br>

</body>
</html>
