<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href=".././css/list.css">
    <title>维护图书</title>
</head>
<body>
<div class="w">
    <header>
        <span>购物车</span>
        <a href="${pageContext.request.contextPath }/cargoods/returnView?viewname=list">
            <input type="button"
                   onclick="javascrtpt:window.location.href='${pageContext.request.contextPath}/cargoods/returnView?viewname=list'"
                   value="返回" class="btn">
        </a>
        <a href="${pageContext.request.contextPath }/cargoods/clearcar">
            <input type="button"
                   onclick="javascrtpt:window.location.href='${pageContext.request.contextPath}/cargoods/clearcar'"
                   value="清空" class="btn">
        </a>
        <a href="${pageContext.request.contextPath }/cargoods/addorders">
            <input type="button"
                   onclick="javascrtpt:window.location.href='${pageContext.request.contextPath}/cargoods/addorders'"
                   value="下单" class="btn">
        </a>
        <%--<a href="${pageContext.request.contextPath }/cargoods/deleteAllCookie" >--%>
        <%--<input type="button" οnclick="javascrtpt:window.location.href='${pageContext.request.contextPath}/cargoods/deleteAllCookie'" value="清空购物车" class="btn">--%>
        <%--</a>--%>
    </header>
    <div class="list">
        <div class="list-bd">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <th width="18%">名称</th>
                    <th width="5%">价格</th>
                    <th width="10%">描述</th>
                    <%--<th width="12%">出厂地址</th>--%>
                    <th width="10%">数量</th>
<%--                    <th width="10%">操作</th>--%>
                </tr>
                <c:forEach items="${list}" var="item">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.price}</td>
                        <td>${item.description}</td>
                            <%--<td>${item.produce}</td>--%>
                        <td>${item.num}</td>
                            <%--<td>${item.type}</td>--%>
<%--                            <td>--%>
<%--                            <a href="${pageContext.request.contextPath}/book/deleteById.action?id=${item.id}">|删除</a>--%>
<%--                            </td>--%>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>