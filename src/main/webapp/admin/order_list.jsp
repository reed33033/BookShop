<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入jstl的核心标签库core -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 引入jstl的格式化库fmt -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//图片的访问路径
	String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath()+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单信息</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.tablelist th {
		text-align: center;
	}
</style>
<script type="text/javascript" src="<%=basePath %>js/jquery-3.3.1.js"></script>

<script type="text/javascript">

// old write 
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});


</script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">订单管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        
        <!-- 
        
        <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li><span><img src="images/t04.png" /></span>统计</li>
         -->
        <li style="cursor: pointer;" onclick="batchDelete()"><span><img src="<%=basePath %>admin/images/t03.png" /></span>批量删除</li>
        </ul>

    </div>
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th><input name="" type="checkbox" value="" checked="checked"/></th>
		        <th>订单编号<i class="sort"><img src="<%=basePath %>admin/images/px.gif" /></i></th>
		        <th>用户</th>
		        <th>总价</th>
		        <th>商品数量</th>
		        <th>订单日期</th>
				<th>订单状态</th>
	        </tr>
	    </thead>
        <tbody>
        	<c:forEach items="${ordersPageInfo.list }" var="orders" varStatus="i">
        		<tr style="text-align: center;">
			        <td><input class="one" name="" type="checkbox" value="${orders.orders_number }" /></td>
					<td>${orders.orders_number }</td>
			        <td>${orders.user.username }</td>
			        <td>${orders.sumPrice }</td>
			        <td>${orders.goodsCount }</td>
			        <td>${orders.create_time }</td>
					<td>
						<c:if test="${orders.state == 0 }">未支付</c:if>
						<c:if test="${orders.state == 1 }"><span style="color: green;">已支付</span></c:if>
					</td>
		        </tr>
        	</c:forEach>
        </tbody>
    </table>
   
    <div class="pagin">
    	<div class="message">
			共<i class="blue">${ordersPageInfo.total }</i>条记录,
			当前显示第&nbsp;<i class="blue">${ordersPageInfo.pageNum}&nbsp;</i>页
			总共&nbsp;<i class="blue">${ordersPageInfo.pages}&nbsp;</i>页
    	</div>
        <ul class="paginList">
			<li class="paginItem"><a href="${pageContext.request.contextPath}/admin/findAllOrders?page=1&size=${ordersPageInfo.pageSize}">首页</a></li>
			<li class="paginItem"><a href="${pageContext.request.contextPath}/admin/findAllOrders?page=${ordersPageInfo.pageNum-1}&size=${ordersPageInfo.pageSize}">上一页</a></li>
			<li class="paginItem"><a href="${pageContext.request.contextPath}/admin/findAllOrders?page=${ordersPageInfo.pageNum+1}&size=${ordersPageInfo.pageSize}">下一页</a></li>
			<li class="paginItem"><a href="${pageContext.request.contextPath}/admin/findAllOrders?page=${ordersPageInfo.pages}&size=${ordersPageInfo.pageSize}">尾页</a></li>
        </ul>
    </div>
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    </div>
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
<script type="text/javascript">

	//点击批量删除之后的方法
	function batchDelete() {
		//定义一个变量来保存id字符串
		var ids = "";
		//获取所有id值	
		$(".one:checked").each(function() {
			//拼接id
			ids += "," + $(this).val();
		})
		if (ids == "") {
			alert("请先选择再删除吧");
			return;
		} else {
			ids = ids.substring(1);
		}
		//将ids传入后台servlet
		if (confirm("您确定要删除吗？")) {
			window.location = "ordersDelete?oid=" + ids;
		}
	}
	
</script>
</html>





















