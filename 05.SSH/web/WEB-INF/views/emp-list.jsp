<%--
  Created by IntelliJ IDEA.
  User: Alex McAvoy
  Date: 2022/2/28
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="S" uri="/struts-tags" %>
<html>
<head>
    <title>员工列表界面</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            //删除
            $(".delete").click(function () {
                var name = $(this).next(":input").val();
                var flag = confirm("是否确认删除" + name + "的信息?");
                //Ajax 删除
                if (flag) {
                    var url = this.href;
                    var args = {"time": new Date()};
                    var $tr = $(this).parent().parent();//当前行
                    $.post(url, args, function (data) {
                        if (data == "1") {
                            alert("删除成功");
                            $tr.remove();
                        } else {
                            alert("删除失败");
                        }
                    });
                }
                return false;
            });
        });
    </script>
</head>
<body>
<h4>员工列表界面</h4>
<s:if test="#request.employees == null || #request.employees.size() == 0">
    无员工信息
</s:if>
<s:else>
    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Email</td>
            <td>Birth</td>
            <td>CreateTime</td>
            <td>Department</td>
            <td>删除</td>
            <td>编辑</td>
        </tr>
        <S:iterator value="#request.employees">
            <tr>
                <td>${id}</td>
                <td>${name}</td>
                <td>${email}</td>
                <td>
                    <s:date name="birth" format="yyyy-MM-dd"/>
                </td>
                <td>
                    <s:date name="createTime" format="yyyy-MM-dd hh:mm:ss"/>
                </td>
                <td>${department.departmentName}</td>
                <td>
                    <a href="emp-delete?id=${id}" class="delete">删除</a>
                    <input type="hidden" value="${name}"/>
                </td>
                <td>
                    <a href="emp-input?id=${id}" > 编辑 </a>
                </td>
            </tr>
        </S:iterator>
    </table>
</s:else>

</body>
</html>
