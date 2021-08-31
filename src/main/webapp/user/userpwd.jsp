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
		<form class="">
			<label>原密码</label>
			<input type="password" class="form-control" name="name" placeholder="原密码"/>
			<label>新密码</label>
			<input type="password" class="form-control" name="mail" placeholder="新密码"/>
			<label>确认密码</label>
			<input type="password" class="form-control" name="phone"  placeholder="确认密码"/>
			<br>
			<button class="btn btn-default" type="submit">修改密码</button>
		</form>
		</div>
	</div>
</div>

<!-- footer -->
<jsp:include page="../footer.jsp" />
<script type="text/javascript">
	var re_LoginPwd = /^(\w){6,20}$/;
	function check_Loginpwd(dom) {
		var val = dom.value;
		console.log(val);
		if (val=='' || !re_LoginPwd.exec(val)) {
			alert("密码由6-20位字母、数字、下划线组成");
		} else {
			$.ajax({
				type: 'get',
				url: '${ctxPath }/UserServlet?opt=valiName',
				data: {loginId: loginId},
				dataType: 'json',
				success: function (data) {
					if (data == true) {
						$('#rg_LoginId_Tips').addClass('collapse');
						$('#rg_LoginId').removeClass('collapse');
						$('#rg_LoginId_state').html('<i class="fa fa-close" style="color:#D10034;"></i>');
						check_Rg_State = false;
					} else {
						$('#rg_LoginId_Tips').addClass('collapse');
						$('#rg_LoginId').addClass('collapse');
						$('#rg_LoginId_state').html('<i class="fa fa-check" style="color:#3ed4a7;"></i>');
						check_Rg_State = true;
					}

				}
			});
		}
	};
</script>
</body>
</html>