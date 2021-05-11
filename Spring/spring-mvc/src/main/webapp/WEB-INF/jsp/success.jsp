<%--
  Created by IntelliJ IDEA.
  User: luhan
  Date: 2021/5/11
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%--isELIgnored="false"表示在本jsp中使用el表达式，否则表示不使用el表达式。
servlet2.4或者更新的版本，isELIgnored默认值为false，
而2.3或者 更早的版本isELIgnored默认值为true。就导致了出现EL表达式无效的情况。 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>success</title>
</head>
<body>
跳转成功！！！当前服务器时间:${date}
</body>
</html>
