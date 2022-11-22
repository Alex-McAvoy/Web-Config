<%--
  Created by IntelliJ IDEA.
  User: Alex McAvoy
  Date: 2022/3/7
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>Test ModelAndView</h4>

    <a href="testModelAndView">Test ModelAndView</a>
    <br><br>

    <a href="testMap">Test Map</a>
    <br><br>

    <a href="testSessionAttributes">Test SessionAttributes</a>
    <br><br>

    <p>Test ModelAttribute</p>
    <!-- 模拟修改操作 -->
    <form action="testModelAttribute" method="post">
        name: Tom <br>
        <input type="hidden" name="name" value="Tom"/>
        password: <input type="text" name="password" value="123456"/><br>
        email: <input type="text" name="email" value="123@qq.com" /><br>
        age: <input type="text" name="age" value="25" /><br>
        province: <input type="text" name="address.province" value="AA" /><br>
        city: <input type="text" name="address.city" value="aa" /><br>
        <input type="submit" value="Submit" />
    </form>

</body>
</html>
