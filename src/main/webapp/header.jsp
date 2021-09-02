<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link rel="stylesheet" href="${ctxPath }/css/bootstrap.css" />
<link rel="stylesheet" href="${ctxPath }/css/fontawesome/css/font-awesome.min.css" />
<link rel="stylesheet" href="${ctxPath }/css/style.css" />
<link rel="stylesheet" href="${ctxPath }/css/header.css" />
<script src="${ctxPath }/js/jquery.js"></script>
<script src="${ctxPath }/js/bootstrap.js"></script>
</head>
<body class="header">
<div class="header-top">
	<div class="tools" style="width: 1115px">
		<div class="tools_list" style="width: 1045px">
			<!--右边列表-->
			<ul class="list_1" style="width: 216px;">
				<li>
					<a href="${pageContext.request.contextPath}/user/userInfo">我的账户</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/orders/findOrdersByUid?uid=${user.uid}">我的订单</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/trolley/findAllTrolley?uid=${user.uid}">我的购物车</a>
				</li>

			</ul>
			<!--左边欢迎光临-->
			<div class="welcome" style="width: 250px;">
							<c:if test="${user==null}">
								欢迎光临网上书城，请
								<a href="${ctxPath}/login.jsp" class="login_link">登录</a>
								&nbsp;<a href="${ctxPath}/register.jsp" class="login_link">免费注册</a>
							</c:if>
							<c:if test="${user!=null}">
								${user.username}，欢迎您来此购书！
								[<a href="${pageContext.request.contextPath}/user/logout" class="login_link">安全退出</a>]
							</c:if>
			</div>
		</div>
	</div>
</div>
<div id="cHeader">

	<div class="row">
		<div class="col-xs-3">
			<a href="${ctxPath}/index.jsp" title="回到首页"><img src="${ctxPath }/images/logo.png" height="80px"/></a>
		</div>
		<div class="col-xs-6">
			<form action="${pageContext.request.contextPath}/books/findBooks"  method="post">
			<!-- 搜索图书 -->
			<div class="input-group" id="search">
				<input type="text" class="form-control" name="info" placeholder="书名、ISBN..."/>
				<span class="input-group-btn">
					<select class="btn btn-defualt" name="option" >
						<option value="bookname">书名</option>
<%--						<option value="byCategoryName">分类</option>--%>
						<option value="ISBN">ISBN</option>
<%--						<option value="byPublish">出版社</option>--%>
					</select>
				</span>
				<span class="input-group-btn">
					<input type="submit" class="btn btn-info" value="搜索" />
				</span>
			</div>
			
			</form>
		</div>
	</div>
</div>
	
<hr class="customhr"/>
</body>
</html>