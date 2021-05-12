<%--
  Created by IntelliJ IDEA.
  User: luhan
  Date: 2021/5/11
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>SpringMvc参数绑定测试</title>
</head>
<body>
<div>
    <h2>SpringMvc参数绑定测试</h2>
    <fieldset>
        <div>SpringMvc支持原生Servlet api测试用例</div>
        <a href="/parameter/bind/primevalServletApi?id=1">点击测试</a>
    </fieldset>

    <fieldset>
        <div>SpringMvc接收简单类型的参数</div>
        <a href="/parameter/bind/simpleParameter?id=2&name=zhangsan">点击测试</a>
    </fieldset>

    <fieldset>
        <div>SpringMvc接收Pojo类型的参数测试</div>
        <a href="/parameter/bind/pojoParameter?id=3&name=lisi">点击测试</a>
    </fieldset>

    <fieldset>
        <div>SpringMvc接收Pojo包装类型的参数测试</div>
        <a href="/parameter/bind/pojoBackParameter?id=4&name=wangwu&user.id=5&user.name=zhaoliu">点击测试</a>
    </fieldset>

    <fieldset>
        <div>SpringMvc接收日期类型的参数</div>
        <a href="/parameter/bind/receiveDateParameter?birthday=1995-09-11">点击测试</a>
    </fieldset>

    <fieldset>
        <div>SpringMvc接收文件测试</div>
        <div>
            <%-- 文件上传到SpringMvc中有几点需要注意：
                1. method="post"
                2. enctype="multipart/form-data"
                3. <input type="file" name="file"> name就是SpringMvc中handler接收文件参数名称
            --%>
            <form method="post" enctype="multipart/form-data" action="/parameter/bind/fileUpload">
                <input type="file" name="file">
                <input type="submit" value="上传">
            </form>
        </div>
    </fieldset>
</div>
</body>
</html>
