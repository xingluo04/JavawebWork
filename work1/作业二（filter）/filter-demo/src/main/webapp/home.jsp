<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2024/10/12
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<h1>欢迎来到主页!</h1>
<p>登录身份: ${sessionScope.user}</p>
<a href="${pageContext.request.contextPath}/login">退出</a>
</body>
</html>

