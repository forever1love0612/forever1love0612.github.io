<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
    <base href="<%=basePath%>">
    
    <title>登录状态校验失败</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<%response.setHeader("refresh", "3;URL=../index.jsp");%>
<font color="red" size="3"> 对不起，您还未登录或登录状态已失效，请您先登录！<br> <br></font>
<font color="black" size="3">三秒后将跳转到登录页面 <br> <br> </font>
如果没有跳转,请按 <a href="index.jsp">这里</a>!!!
<br> 

  </body>

</html>
