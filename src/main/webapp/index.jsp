<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<html>
	<head>
		<meta charset="utf-8">
		<title>首页 - 在线购书</title>
		<link rel="stylesheet" href="${ctxPath }/css/slide.css" charset="utf-8"/>
		<script type="text/javascript" src="${ctxPath }/js/jquery.js"></script>
		<script src="${ctxPath }/js/slide.js" ></script>
		<script type="text/javascript">
			$(function(){
				initSlide();
			});
		</script>
	</head>
	<body>
		<!-- header -->
		<jsp:include page="header.jsp" />
		
		<!-- content -->
		<div id="container">
			<!--图书分类列表 -->
			<ul class="cateul">
				<li class="catehead">图书分类</li>
				<c:forEach items="${cateList}" var="c">
					<li><a href="<%=basePath %>books?func=findBookByInfo&option=cid&info=${c.cid }">${c.cname}</a></li>
				</c:forEach>
			</ul>
			
			<div class="content">
				<!-- 幻灯片 -->
				<div id="slide">
					<ul>
					<li>
						<div style="left:0;top:0;"><a href="#"><img width="780" height="420" src="images/slide/01.jpg" alt="" /></a></div>
					</li>
					<li>
						<div style="left:0;top:0;"><a href="#"><img width="780" height="420" src="images/slide/1.jpg" alt="" /></a></div>
					</li>
						<li>
							<div style="left:0;top:0;"><a href="#"><img width="780" height="420" src="images/slide/4.jpg" alt="" /></a></div>
						</li>
						<li>
							<div style="left:0;top:0;"><a href="#"><img width="780" height="420" src="images/slide/5.jpg" alt="" /></a></div>
						</li>
					</ul>
				</div>
				
				<!-- 人气推荐 -->
				<div class="goodsbar">
					<span class="goodsbar-title">人气图书</span>
					<div class="goodsbar-content">
						<c:forEach var="book" items="${hotBList }" begin="0" end="3">
							<dl class="bookBlock">
								<dd class="img"><a href="<%=basePath %>books?func=buyBook&bid=${book.bid}"><img  width="125px" height="150px" src="${book.pic}" class="" /></a></dd>
								<dt><a href="<%=basePath %>books?func=buyBook&bid=${book.bid}">${book.bname}</a></dt>
								<dd class="price">单价:￥<span class="unitprice">${book.price}</span>
<%--									<span class="pull-right">浏览&nbsp;${book.clicks}</span></dd>--%>
							</dl>
						</c:forEach>
					</div>
				</div>
			</div>
			
		</div>

		<!-- footer -->
		<jsp:include page="footer.jsp" />
	</body>
</html>
