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
		<h4>我的资料</h4><hr/>
		<div class="updateuser">
		<!-- 
		<div class="alert alert-warning alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  ${MSG_USER_UPDATE_RESULT}
		</div>
		 -->
		<form action="${ctxPath }/user?func=updateUser" method="post">
			<input type="hidden" name="uid" value="${user.uid }" />
			<label>用户名</label>
			<input type="text" class="form-control" name="username" placeholder="用户名" value="${user.username }"/>
			<label>姓名</label>
			<input type="text" class="form-control" name="uname" placeholder="真实姓名" value="${user.uname }"/>
			<label>手机号码</label>
			<input type="text" class="form-control" name="phone"  placeholder="手机号码" value="${user.phone }"/>
			<label>密码</label>
			<input type="text" class="form-control" name="password"  placeholder="密码" value="${user.password }"/>
			<label>联系地址</label>
			<textarea rows="3" cols="" class="form-control" name="area" placeholder="联系地址"> ${user.area}</textarea>
			<br>
			<button class="btn btn-default" type="submit">修改资料</button>
		</form>
		</div>
	</div>
</div>

<!-- footer -->
<jsp:include page="../footer.jsp" />

<!-- script -->
<script type="text/javascript">
	$(function(){
		//处理提示消息
		if('${MSG_USER_UPDATE_RESULT}'==''){
			$('#msg').addClass('collapse');
			<% session.removeAttribute("MSG_USER_UPDATE_RESULT"); %>	
		}else
			$('#msg').removeClass('collapse');
	});
	
</script>
</body>
</html>