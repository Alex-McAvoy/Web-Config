<%--
  Created by IntelliJ IDEA.
  User: Alex McAvoy
  Date: 2022/2/28
  Time: 2:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>员工录入界面</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        //登录名验证
        $(function(){
            $(":input[name=name]").change(function(){
                var val = $(this).val();
                val = $.trim(val);
                var $this = $(this);

                if(val != ""){
                    //把当前节点后面的所有 font 兄弟节点删除
                    $this.nextAll("font").remove();

                    var url = "validateName";
                    var args = {"name":val};
                    $.post(url, args, function(data){
                        //表示可用
                        if(data == "1"){
                            $this.after("<font color='green'>Name可用!</font>");
                        }
                        //不可用
                        else if(data == "0"){
                            $this.after("<font color='red'>Name不可用!</font>");
                        }
                        //服务器错误
                        else{
                            alert("服务器错误!");
                        }
                    });
                }else{
                    alert("Name 不能为空");
                    $(this).val("");
                    $this.focus();
                }
            });
        })
    </script>
</head>
<body>
<h4>员工录入界面</h4>
<s:form action="emp-save" method="POST">
    <s:if test="id != null">
        <s:textfield name="name" label="name" disabled="true"></s:textfield>
        <s:hidden name="id"></s:hidden>
        <%--
        <!-- 通过添加隐藏域的方式把未显式提交的字段值提交到服务器 -->
        <s:hidden name="name"></s:hidden>
        <s:hidden name="createTime"></s:hidden>
        --%>
    </s:if>
    <s:else>
        <s:textfield name="name" label="name"></s:textfield>
    </s:else>

    <s:textfield name="email" label="Email"></s:textfield>
    <s:textfield name="birth" label="Birth"></s:textfield>

    <s:select list="#request.departments" listKey="id" listValue="departmentName"
              name="department.id" label="Department"></s:select>
    <s:submit></s:submit>
</s:form>

</body>
</html>
