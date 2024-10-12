<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2024/10/12
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>登录</title>
</head>
<body>
<h2>登录</h2>
<form action="login" method="post">
  账号: <input type="text" name="username" required><br>
  密码: <input type="password" name="password" required><br>
  <button type="submit">登录</button>
</form>
<% if (request.getAttribute("loginError") != null) { %>
<p style="color: red;"><%= request.getAttribute("loginError") %></p>
<% } %>
</body>
</html>

