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
				<c:forEach items="${applicationScope.cates}" var="c">
					<li><a href="<%=basePath %>books?func=findBookByInfo&option=cid&info=${c.cid }">${c.cname}</a></li>
				</c:forEach>
			</ul>
			
			
			<div class="content">
				<div class="goodsbar">
					<span class="goodsbar-title">搜索结果
					<ul class="pull-right">
					<li style="float: left;">当前第${pageTool.currentPage }页,</li>
						<li style="float: left;"><a href="<%=basePath %>books?func=findBookByInfo&option=${option}&info=${info}&currentPage=1">首页</a></li>
					<li style="float: left;">
						<a href="<%=basePath %>books?func=findBookByInfo&option=${option}&info=${info}&currentPage=${pageTool.lastPage }">上一页</a>
<%--						<c:if test="${searchBooks.currentPage<=1}">--%>
<%--							<a href="${ctxPath}/BookServlet?opt=search&page=${searchBooks.currentPage}">--%>
<%--							上一页--%>
<%--							</a>--%>
<%--						</c:if>--%>
<%--						<c:if test="${searchBooks.currentPage>1}">--%>
<%--							<a href="${ctxPath}/BookServlet?opt=search&page=${searchBooks.currentPage-1}">--%>
<%--							上一页--%>
<%--							</a>--%>
<%--						</c:if>--%>
					</li>
					<li style="float: left;">
						<a href="<%=basePath %>books?func=findBookByInfo&option=${option}&info=${info}&currentPage=${pageTool.nextPage }">下一页</a>
<%--						<c:if test="${searchBooks.currentPage<searchBooks.totalPage}">--%>
<%--							<a href="${ctxPath}/BookServlet?opt=search&page=${searchBooks.currentPage+1}">--%>
<%--							下一页--%>
<%--							</a>--%>
<%--						</c:if>--%>
<%--						<c:if test="${searchBooks.currentPage>=searchBooks.totalPage}">--%>
<%--							<a href="${ctxPath}/BookServlet?opt=search&page=${searchBooks.totalPage}">--%>
<%--							下一页--%>
<%--							</a>--%>
<%--						</c:if>--%>
					</li>
								<li style="float: left;"><a href="<%=basePath %>books?func=findBookByInfo&option=${option}&info=${info}&currentPage=${pageTool.pageSize }">尾页</a></li>
					<li style="float: left;">共${pageTool.pageSize }页</li>
				</ul>
				</span>
					<div class="goodsbar-content">
						<!-- 搜索结果 -->
						<c:if test="${searchBooks ==null}" >
							没有搜索到相关图书！
						</c:if>
						<c:if test="${searchBooks !=null}" >
							<c:forEach items="${searchBooks}" var="book" >
								<dl class="bookList">
									<dt><a href="<%=basePath %>books?func=buyBook&bid=${book.bid}"><img  width="125px" height="150px" src="${book.pic}" class="" /></a></dt>
									<dd class="title"><a href="<%=basePath %>books?func=buyBook&bid=${book.bid}">${book.bname}</a></dd>
									<dd class="price">￥${book.price}</dd>
									<dd class="info">${book.author}</dd>
									<dd class="intro"><!-- ${book.description} --></dd>
									<dd class=""><a href="<%=basePath %>books?func=buyBook&bid=${book.bid}" class="btn btn-danger">立即购买</a></dd>
								</dl>
							</c:forEach>
						</c:if>
					</div>
				</div>
				<!-- 分页 -->
				<ul class="pull-right" id="pager">
					<li style="float: left;">当前第${pageTool.currentPage }页,</li>
					<li style="float: left;"><a href="<%=basePath %>books?func=findBookByInfo&option=${option}&info=${info}&currentPage=1">首页</a></li>
					<li style="float: left;">
						<a href="<%=basePath %>books?func=findBookByInfo&option=${option}&info=${info}&currentPage=${pageTool.lastPage }">上一页</a>
<%--						<c:if test="${searchBooks.currentPage<=1}">--%>
<%--							<a href="${ctxPath}/BookServlet?opt=search&page=${searchBooks.currentPage}">--%>
<%--							上一页--%>
<%--							</a>--%>
<%--						</c:if>--%>
<%--						<c:if test="${searchBooks.currentPage>1}">--%>
<%--							<a href="${ctxPath}/BookServlet?opt=search&page=${searchBooks.currentPage-1}">--%>
<%--							上一页--%>
<%--							</a>--%>
<%--						</c:if>--%>
					</li>
					<li style="float: left;">
						<a href="<%=basePath %>books?func=findBookByInfo&option=${option}&info=${info}&currentPage=${pageTool.nextPage }">下一页</a>
<%--						<c:if test="${searchBooks.currentPage<searchBooks.totalPage}">--%>
<%--							<a href="${ctxPath}/BookServlet?opt=search&page=${searchBooks.currentPage+1}">--%>
<%--							下一页--%>
<%--							</a>--%>
<%--						</c:if>--%>
<%--						<c:if test="${searchBooks.currentPage>=searchBooks.totalPage}">--%>
<%--							<a href="${ctxPath}/BookServlet?opt=search&page=${searchBooks.totalPage}">--%>
<%--							下一页--%>
<%--							</a>--%>
<%--						</c:if>--%>
					</li>
					<li style="float: left;"><a href="<%=basePath %>books?func=findBookByInfo&option=${option}&info=${info}&currentPage=${pageTool.pageSize }">尾页</a></li>
					<li style="float: left;">共${pageTool.pageSize }页</li>

				</ul>
			</div>
		</div>
		
		<!-- footer -->
		<jsp:include page="footer.jsp" />
	</body>
</html>
