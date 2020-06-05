<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/list.css">
    <title>维护图书</title>
</head>
<body>
<div class="w">
    <div class="list">
        <div class="list-bd">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <th width="18%">编号</th>
                    <th width="5%">价格</th>
                    <th width="10%">操作</th>
                    <%--<th width="10%">数量</th>--%>
                </tr>
                <c:forEach items="${list}" var="item">
                    <tr>
                        <td>${item.number}</td>
                        <td>${item.price}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/cargoods/queryordersdetails?id=${item.number}">订单详情</a>
                            <a href="${pageContext.request.contextPath}/cargoods/teardowndetails?orderId=${item.number}">拆单</a>
                        </td>
                            <%--<td>${item.produce}</td>--%>
                            <%--<td>${item.num}</td>--%>
                            <%--<td>${item.type}</td>--%>
                            <%--<td>--%>
                            <%--<a href="${pageContext.request.contextPath}/book/show.action?id=${item.id}">查看</a>--%>
                            <%--<a href="${pageContext.request.contextPath}/book/queryById.action?id=${item.id}">|修改</a>--%>
                            <%--<a href="${pageContext.request.contextPath}/book/deleteById.action?id=${item.id}">|删除</a>--%>
                            <%--</td>--%>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>