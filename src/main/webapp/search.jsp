<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<html>
	<head>
		<meta charset="utf-8">
		<title>搜索结果 - 在线购书</title>
		<script type="text/javascript" src="js/jquery.js"></script>
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
					<li><a href="${pageContext.request.contextPath}/books/findBooks?info=${c.cid}&option=cid">${c.cname}</a></li>
				</c:forEach>
			</ul>
			
			
			<div class="content">
				<div class="goodsbar">
					<span class="goodsbar-title">搜索结果
					<ul class="pull-right">
					<li style="float: left;">当前第${searchBooks.pageNum}页,</li>
						<li style="float: left;"><a href="${pageContext.request.contextPath}/books/findBooks?info=${info}&option=${option}&page=1&size=${searchBooks.pageSize}">首页</a></li>
					<li style="float: left;">
						<a href="${pageContext.request.contextPath}/books/findBooks?info=${info}&option=${option}&page=${searchBooks.pageNum-1}&size=${searchBooks.pageSize}">上一页</a>
					</li>
					<li style="float: left;">
						<a href="${pageContext.request.contextPath}/books/findBooks?info=${info}&option=${option}&page=${searchBooks.pageNum+1}&size=${searchBooks.pageSize}">下一页</a>
					</li>
								<li style="float: left;"><a href="${pageContext.request.contextPath}/books/findBooks?info=${info}&option=${option}&page=${searchBooks.pages}&size=${searchBooks.pageSize}">尾页</a></li>
					<li style="float: left;">共${searchBooks.pages }页</li>
				</ul>
				</span>
					<div class="goodsbar-content">
						<!-- 搜索结果 -->
						<c:if test="${searchBooks.list ==null}" >
							没有搜索到相关图书！
						</c:if>
						<c:if test="${searchBooks.list !=null}" >
							<c:forEach items="${searchBooks.list}" var="book" >
								<dl class="bookList">
									<dt><a href="${pageContext.request.contextPath}/books/findBooksBybid?bid=${book.bid}"><img  width="125px" height="150px" src="${book.pic}" class="" /></a></dt>
									<dd class="title"><a href="${pageContext.request.contextPath}/books/findBooksBybid?bid=${book.bid}">${book.bname}</a></dd>
									<dd class="price">￥${book.price}</dd>
									<dd class="info">${book.author}</dd>
									<dd class="intro"><!-- ${book.description} --></dd>
									<dd class=""><a href="${pageContext.request.contextPath}/books/findBooksBybid?bid=${book.bid}" class="btn btn-danger">立即购买</a></dd>
								</dl>
							</c:forEach>
						</c:if>
					</div>
				</div>
				<!-- 分页 -->
				<ul class="pull-right" id="pager">

					<li style="float: left;">当前第${searchBooks.pageNum}页,</li>
					<li style="float: left;"><a href="${pageContext.request.contextPath}/books/findBooks?info=${info}&option=${option}&page=1&size=${searchBooks.pageSize}">首页</a></li>
					<li style="float: left;">
						<a href="${pageContext.request.contextPath}/books/findBooks?info=${info}&option=${option}&page=${searchBooks.pageNum-1}&size=${searchBooks.pageSize}">上一页</a>
					</li>
					<li style="float: left;">
						<a href="${pageContext.request.contextPath}/books/findBooks?info=${info}&option=${option}&page=${searchBooks.pageNum+1}&size=${searchBooks.pageSize}">下一页</a>
					</li>
					<li style="float: left;"><a href="${pageContext.request.contextPath}/books/findBooks?info=${info}&option=${option}&page=${searchBooks.pages}&size=${searchBooks.pageSize}">尾页</a></li>
					<li style="float: left;">共${searchBooks.pages }页</li>
				</ul>
			</div>
		</div>
		
		<!-- footer -->
		<jsp:include page="footer.jsp" />
	</body>
</html>
