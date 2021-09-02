<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码 - 个人中心</title>
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
		<h4>修改密码</h4><hr/>
		<div class="updateuser">
		<form class="updatePwd" method="post" action="${pageContext.request.contextPath}/user/updatePassword">
<%--			<label>原密码</label>--%>
<%--			<input type="password" class="form-control" name="name" placeholder="原密码" value="${user.password}" readonly/>--%>
			<label>新密码</label>
			<input type="password" class="form-control" name="password" placeholder="新密码" onblur="checkLoginPwd(this)"/>
			<label>确认密码</label>
			<input type="password" class="form-control" placeholder="确认密码"  onblur="checkLoginPwd_Success(this)"/>
			<br>
			<button class="btn btn-default" type="submit">修改密码</button>
		</form>
		</div>
	</div>
</div>

<!-- footer -->
<jsp:include page="../footer.jsp" />
<script type="text/javascript">
	var check_Rg_pwd='';
	//密码
	function checkLoginPwd(dom){
		var re_LoginPwd = /^(\w){6,20}$/;
		var val= dom.value;
		if(val=='' || !re_LoginPwd.exec(val)){
			alert("密码由6-20位字母、数字、下划线组成");
		}else {
			check_Rg_pwd = val;
		}
	}
	//确认密码
	function checkLoginPwd_Success(dom){
		var val= dom.value;
		if(val=='' || check_Rg_pwd=='' || val!=check_Rg_pwd){
			alert("两次密码不一致");
		}
	}

</script>
</body>
</html>