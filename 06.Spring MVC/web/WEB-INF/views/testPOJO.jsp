<%--
  Created by IntelliJ IDEA.
  User: Alex McAvoy
  Date: 2022/3/6
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h4>Test POJO</h4>

    <form action="testPOJO" method="post">
        username: <input type="text" name="username"/><br>
        password: <input type="text" name="password"/><br>
        email: <input type="text" name="email"/><br>
        age: <input type="text" name="age"/><br>
        province: <input type="text" name="address.province"/><br>
        city: <input type="text" name="address.city"/><br>
        <input type="submit" name="Submit"/><br>
    </form>

</body>
</html>
