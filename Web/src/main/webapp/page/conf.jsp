<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Shirtiny
  Date: 2019/7/26
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>用户属性</title>
</head>
<body>
<%--将用户对象在session中命名为user--%>
<security:authentication property="principal" var="user" scope="session"/>
<p>${user}</p>
<%--直接输出值--%>
<security:authentication property="principal.username"/>
</body>
</html>
