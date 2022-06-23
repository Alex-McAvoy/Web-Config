<%--
  Created by IntelliJ IDEA.
  User: Alex McAvoy
  Date: 2022/3/15
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#name").change(function(){
                var val = $(this).val();
                val = $.trim(val);
                $(this).val(val);

                //修改状态下的用户名校验
                var _oldName = $("#_oldName").val();
                _oldName = $.trim(_oldName);
                if(_oldName != null && _oldName != "" && _oldName == val){
                    alert("name 可用");
                    return;
                }

                //用户名验证
                var url = "${pageContext.request.contextPath }/ajaxValidateName";
                var args = {"name":val,"date":new Date()};

                $.post(url, args, function(data){
                    if(data == "0"){
                        alert("name 可用");
                    }else if(data == "1"){
                        alert("name 不可用");
                    }else{
                        alert("网络或程序出错");
                    }
                });
            });
        })
    </script>
</head>
<body>

    <c:set value="${pageContext.request.contextPath }/input" var="url"></c:set>
    <c:if test="${employee.id != null }">
        <c:set value="${pageContext.request.contextPath }/input/${employee.id}" var="url"></c:set>
    </c:if>

    <form:form action="${url }" method="POST" modelAttribute="employee">
        <!-- 修改状态 Ajax 验证 -->
        <c:if test="${employee.id != null }">
            <input type="hidden" id="_oldName" value="${employee.name }"/>
            <form:hidden path="id"/>
            <input type="hidden" name="_method" value="PUT"/>
        </c:if>
        <!-- 表单 -->
        Name: <form:input path="name" id="name"/> <br>
        Email: <form:input path="email"/> <br>
        Birth: <form:input path="birth"/> <br>
        Department:
        <form:select path="department.id" items="${departments }"
                     itemLabel="departmentName" itemValue="id"></form:select> <br>
        <input type="submit" value="Submit"/>
    </form:form>

</body>
</html>
