<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.api.*"%>
<%@ page import="com.alipay.api.request.*"%>
<%@ page import="com.alipay.config.AlipayConfig" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>交易关闭</title>
</head>

<%
	//获得初始化的AlipayClient
	AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
	
	//设置请求参数
	AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
	//商户订单号，商户网站订单系统中唯一订单号
	String out_trade_no = new String(request.getParameter("WIDTCout_trade_no").getBytes("UTF-8"),"UTF-8");
	//支付宝交易号
	String trade_no = new String(request.getParameter("WIDTCtrade_no").getBytes("UTF-8"),"UTF-8");
	//请二选一设置
	
	alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," +"\"trade_no\":\""+ trade_no +"\"}");
	
	//请求
	String result = alipayClient.execute(alipayRequest).getBody();
	
	//输出
	out.println(result);

%>
<body>
</body>
</html>