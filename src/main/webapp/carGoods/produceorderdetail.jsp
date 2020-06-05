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
    <header>
        <span>工厂生产订单</span>
        <a href="${pageContext.request.contextPath }/cargoods/returnView?viewname=orderlist">
            <input type="button"
                   onclick="javascrtpt:window.location.href='${pageContext.request.contextPath}/cargoods/returnView?viewname=orderlist'"
                   value="返回" class="btn">
        </a>
    </header>
    <div class="list">
        <div class="list-bd">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <th width="8%">工厂名称</th>
                    <th width="10%">货物名称</th>
                    <th width="5%">数量</th>
                    <th width="5%">价格</th>
                    <th width="8%">客户订单号</th>
                </tr>
                <c:forEach items="${list}" var="item">
                    <tr>
                        <td>${item.produce}</td>
                        <td>${item.goods_name}</td>
                        <td>${item.tot_num}</td>
                        <td>${item.tot_price}</td>
                        <td>${item.order_id}</td>
                            <%--<td>${item.type}</td>--%>
                            <%--<td>--%>
                            <%--<td><a href="${pageContext.request.contextPath}/cargoods/addGoodsToCart?goodsId=${item.id}">添加购物车</a>--%>
                            <%--</td>--%>
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