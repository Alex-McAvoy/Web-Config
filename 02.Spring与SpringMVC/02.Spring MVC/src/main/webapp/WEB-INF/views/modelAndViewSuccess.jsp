<%--
  Created by IntelliJ IDEA.
  User: Alex McAvoy
  Date: 2022/3/8
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>Test ModelAndView</h4>
    time: ${requestScope.time}
    <br><br>

    <h4>Test Map</h4>
    names: ${requestScope.names}
    <br><br>

    <h4>Test SessionAttributes</h4>
    user: ${sessionScope.user} <br><br>
    school: ${sessionScope.school}
<br><br>

</body>
</html>
