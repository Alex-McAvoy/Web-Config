<%--
  Created by IntelliJ IDEA.
  User: Alex McAvoy
  Date: 2022/3/6
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4> Test @RequestMapping</h4>
    <a href="testRequestMapping">Test RequestMapping</a>
    <br><br>

    <form action="testRequestMappingMethod" method="post">
        <input type="submit" value="Test RequestMapping Method"/>
    </form>
    <br><br>

    <a href="testRequestMappingParam?username=tom&age=10">Test RequestMapping Param</a> 访问不成功(age=10)
    <br><br>
    <a href="testRequestMappingParam?username=tom&age=11">Test RequestMapping Param</a>
    <br><br>

    <a href="testRequestMappingHeader">Test RequestMapping Header</a>
    <br><br>

    <a href="testAntPath/xxx1/abc">Test Ant Path</a>
    <br><br>
    <a href="testAntPath/xxx2/abc">Test Ant Path</a>
    <br><br>

    <a href="testPathVariable/1">Test PathVariable</a>
    <br><br>

    <a href="testRequestParam?username=tom&age=25">Test RequestParam</a>
    <br><br>

    <a href="testRequestHeader">Test RequestHeader</a>
    <br><br>

    <a href="testCookieValue">Test CookieValue</a>
    <br><br>

    <a href="testRedirect">Test Redirect</a>
    <br><br>

    <a href="testForward">Test Redirect</a>
    <br><br>


</body>
</html>
