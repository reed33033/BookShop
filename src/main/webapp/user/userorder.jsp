<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!-- 引入jstl的格式化库fmt -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单 - 个人中心</title>
</head>
<body>
<!-- header -->
<jsp:include page="../header.jsp" />
		
<div id="container">
	<!-- 个人中心菜单 -->
	<div class="usermenu pull-left">
		<jsp:include page="usermenu.jsp" />
	</div>
	
	<!-- info -->
	<div class="useropt pull-right">
		<h4>我的订单</h4><br/>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>订单号</th>
<%--					<th>图书</th>--%>
					<th width="60">数量</th>
					<th width="80">总价</th>
					<th>时间</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${olist!=null}">
				<c:forEach items="${olist}" var="o">
					<tr>
						<td>${o.orders_number }</td>
<%--						<td>${o.trolleys.books.bname }</td>--%>
						<td>${o.goodsCount }</td>
						<td>￥${o.sumPrice}</td>
						<td><fmt:formatDate value="${o.create_time }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
				</c:if>
			</tbody>
		</table>
		<c:if test="${olist==null}">
			没发现有购买订单哦！赶紧<a href="${ctxPath }/index.jsp">去选购</a>吧！
		</c:if>
	</div>
</div>

<!-- footer -->
<jsp:include page="../footer.jsp" />
</body>
</html>