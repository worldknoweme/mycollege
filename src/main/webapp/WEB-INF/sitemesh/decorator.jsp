<%--
  Created by IntelliJ IDEA.
  User: wang-jl
  Date: 2018/1/1
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        <sitemesh:write property="title"></sitemesh:write>
    </title>
    <sitemesh:write property='head' />
</head>
<body>
<sitemesh:write property='title' /><br />
<sitemesh:write property='body' />
<hr />
<footer>footer</footer>
</body>
</html>
