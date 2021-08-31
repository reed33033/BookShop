<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath()+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改商品</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="<%=basePath %>admin/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    	<li><a href="#">修改商品</a></li>
	    </ul>
    </div>
    <div class="formbody">
    	<div class="formtitle"><span>商品信息</span></div>
	    <form action="<%=basePath %>books" method="post" enctype="multipart/form-data">
	   		<!-- 修改的功能方法 -->
	    	<input type="hidden" name="func" value="updateBooks" />
	    	<!-- 修改的商品主键id -->
	    	<input type="hidden" name="bid" value="${books.bid }" />
		    <ul class="forminfo">
		    	<!-- 商品分类信息 -->
		    	<li>
		    		<label>分类</label>
		    		<select name="cid" class="dfinput">
		    			<option value="0">===请选择===</option>
		    			<c:forEach items="${cates }" var="cate">
		    				<option <c:if test="${books.cid == cate.cid }">selected</c:if> value="${cate.cid }">${cate.cname }</option>
		    			</c:forEach>
		    		</select>
		    	</li>
			    <li><label>名称</label><input value="${books.bname }" name="bname" type="text" class="dfinput" /><i>名称不能超过30个字符</i></li>
			    <li><label>作者</label><input value="${books.author }" name="author" type="text" class="dfinput" /></li>
			    <li><label>出版社</label><input value="${books.publish }" name="publish" type="text" class="dfinput" /></li>
				<li><label>ISBN</label><input value="${books.isbn }" name="isbn" type="text" class="dfinput" /></li>
				<li><label>字数</label><input value="${books.words }" name="words" type="text" class="dfinput" /></li>
			    <li><label>单价</label><input value="${books.price }" name="price" type="text" class="dfinput" /></li>
			    <li><label>简介</label>
			    	<textarea name="description" cols="10" rows="10" class="textinput" style="height: 80px">${books.description }</textarea>
			    </li>
			    <li><label>完整介绍</label>
			    	<textarea name="full_description" cols="10" rows="10" class="textinput" style="height: 80px">${books.full_description }</textarea>
			    </li>
			    <li><label>封面</label>
			    	<input name="pic" type="file"/><font style="color: red">${msg }</font>
			    	<!-- 显示当前的商品图片 -->
			    	<img src="<%=imgPath %>${books.pic }" width="80" height="80" />
			    	<!-- 将之前的图片名称存放在隐藏域中，不显示，但是后台可以接收 -->
			    	<input type="hidden" name="oldPic" value="${books.pic }" />
			    </li>
			    <li><label>商品小类别</label>
				    <cite>
					    <input <c:if test="${books.state == 0 }">checked</c:if> name="state" type="radio" value="0" />正常&nbsp;&nbsp;&nbsp;&nbsp;
					    <input <c:if test="${books.state == 1 }">checked</c:if> name="state" type="radio" value="1" />人气推荐
				    </cite>
			    </li>
			    <li><label>版本</label><input value="${books.version }" name="version" type="text" class="dfinput" /></li>
			    <li><label>出版日期</label>
			    	<input value="<fmt:formatDate value="${books.product_date }" pattern="yyyy-MM-dd HH:mm:ss" />" class="Wdate" style="width: 345px;height: 32px;line-height: 32px;" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="product_date" type="text" class="dfinput" />
			    </li>
			    <li><label>&nbsp;</label><input type="submit" class="btn" value="确认保存"/></li>
		    </ul>
	    </form>
    </div>
</body>
</html>
