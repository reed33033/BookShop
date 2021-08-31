<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心 - 在线购书</title>
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
		<h4>个人中心</h4><hr/>
		<div class="updateuser">
			您好,${user.username }
		</div>
	</div>
</div>

<!-- footer -->
<%--<jsp:include page="../footer.jsp" />--%>
</body>
</html>