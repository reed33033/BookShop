<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="list-group">
	<a class="list-group-item" style="background-color: #3ed4a7;border:1px solid #d0d0d0;color:#fff;">个人操作</a>
	<a href="${ctxPath }/user/user.jsp" class="list-group-item">
		<i class="fa fa-home"></i>&nbsp;&nbsp;个人中心
	</a>
	<a href="${ctxPath }/trolley?func=findAllTrolley" class="list-group-item">
		<i class="fa fa-shopping-cart"></i>&nbsp;&nbsp;我的购物车
<%--		<c:if test="${trolleys.number==null }">--%>
<%--			<span class="badge">0</span>--%>
<%--		</c:if>--%>
<%--		<c:if test="${trolleys.number!=null }">--%>
<%--			<span class="badge">${trolleys.number}</span>--%>
<%--		</c:if>--%>
	</a>
	<a href="${ctxPath }/orders?func=showInfo" class="list-group-item">
		<i class="fa fa-reorder"></i>&nbsp;&nbsp;我的订单
	</a>
	<a href="${ctxPath }/user/userinfo.jsp" class="list-group-item">
		<i class="fa fa-user"></i>&nbsp;&nbsp;我的资料
	</a>

	<a href="${ctxPath }/user?func=userLogout" class="list-group-item">
		<i class="fa fa-sign-out"></i>&nbsp;&nbsp;安全退出
	</a>
</div>
</body>
</html>