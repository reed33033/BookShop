<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的购物车 - 个人中心</title>
	<script type="text/javascript">

		//去结算点击的方法
		function pay(sum_price, goods_count, tids) {
			alert(tids);
			//做金额或者件数的非空判断
			if (sum_price == 0 || goods_count == 0) {
				alert("请先购物消费吧");
				window.location = "index.jsp";
			} else {
				window.location = "/orders/ordersSave?sumPrice=" + sum_price + "&goodsCount=" + goods_count;
			}
		}

		//删除购物车的方法
		function deleteTrolley(tid) {
			if (confirm("确定要删除吗？？？")) {
				window.location = "/trolley/trolleyDelete?tid=" + tid+"&uid="+${user.uid};
			}
		}

		//修改购物车中商品数量的代码
		function addOrDeleteNumber(tid, number) {
			//判断临界情况
			if (number < 1) {
				number = 1;
				//为中间显示商品数量的输入框重新赋值
				$("#num").val(number);
				alert("商品数量不能小于1，如果你不想买了，请删除此条记录");
			}
			if (number > 5) {
				number = 5;
				$("#num").val(number);
				alert("每人限购五件，库存不足~");
			}
			window.location = "/trolley/addOrDeleteNumber?tid=" + tid + "&number=" + number+"&uid="+${user.uid};
		}

	</script>
</head>
<body>
<!-- header -->
<jsp:include page="../header.jsp" />

<!-- 定义一个变量来存储总金额 -->
<c:set var="sum_price" value="0"></c:set>
<!-- 定义一个变量来存储商品数量 -->
<c:set var="goods_count" value="0"></c:set>
		
<div id="container">
	<!-- 个人中心菜单 -->
	<div class="usermenu pull-left">
		<jsp:include page="usermenu.jsp" />
	</div>
	
	<!-- info -->
	<div class="useropt pull-right">
		<h4>我的购物车</h4><br>
		<table class="table table-hover">
			<thead>
			<tr>
				<th>图书</th>
				<th>单价(元)</th>
				<th width="120px" class="text-center">数量</th>
				<th>小计(元)</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${trolleys!=null}">
			<c:forEach items="${trolleys}" var="trolley">
			<tr>
				<td><a href="${pageContext.request.contextPath}/books/findBooksBybid?bid=${trolley.bid}">${trolley.books.bname }</a></td>
				<td>￥${trolley.books.price }</td>
				<td>
					<div class="input-group input-group-sm">
						<a href="javascript:void(0)" class="input-group-addon"  style="text-decoration: none;" onclick="addOrDeleteNumber(${trolley.tid}, ${trolley.number - 1 })">-</a>
						<input id="num" type="text" value="${trolley.number }" class="form-control input-sm text-center"/>

						<c:set var="goods_count" value="${goods_count + trolley.number }"></c:set>

						<a href="javascript:void(0)" class="input-group-addon" style="text-decoration: none;" onclick="addOrDeleteNumber(${trolley.tid}, ${trolley.number + 1 })">+</a>

					</div>
				</td>
					<%--						<td>${s.item.unitPrice*s.amount }元</td>--%>
				<c:set var="sum_price" value="${sum_price + trolley.books.price * trolley.number }"></c:set>
				<td>${trolley.books.price * trolley.number }元</td>
				<td><a href="javascript:void(0)" onclick="deleteTrolley(${trolley.tid })">删除</a></td>
			</tr>
			</c:forEach>
			</c:if>

		</table>

		<div class="pull-right">
			<span class="show_total">总计:<i>￥${sum_price }</i></span>
			<a href="javascript:void(0)" class="btn btn-danger  btn-sm" onclick="pay(${sum_price}, ${goods_count }, '${tids }')">去结算</a>
			<%--					<a  class="btn btn-warning btn-sm" >清空购物车</a>--%>
		</div>
	</div>
</div>

<!-- footer -->
<jsp:include page="../footer.jsp" />


</body>
</html>