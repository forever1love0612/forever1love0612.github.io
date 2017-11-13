<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>欢迎使用上位机控制平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">   
<Style>
a:link,a:visited
{

font-weight:bold;
font-size:24px;
font-family:Verdana, Arial, Helvetica, sans-serif;
color:#FFFFFF;
background-color:#98bf21;
width:200px;
text-align:center;
padding:40px;
text-decoration:none;
}

a:hover,a:active
{
background-color:#7A991A;
}
</style>
<style>

.botCenter{width:100%; height:35px; line-height:35px; background:#ccc; position:fixed; bottom:25px; left:0px; font-size:14px; color:#000; text-align:center;}

</style>
  </head>
  
  <body>
   <img src="<%=request.getContextPath()%>/pictures/title.jpg" />
    <center>  
 
    <br><br>  <br><br>   <br><br>   <br><br>
        <s:a action="info_onlyGetState" namespace="/info">查看电路状态</s:a>
        <s:a action="info_queryAllState" namespace="/info">管理状态数据</s:a>    
        <s:a action="info_queryAllInfo" namespace="/info">管理通信数据</s:a> 
        <s:a action="user_queryAllUser" namespace="/user">管理用户数据</s:a> 
        <s:a action="user_logout" namespace="/user">注销登录</s:a> 

    </center>
         <div class="botCenter">____________________________________</div>
         <div class="botCenter">designed by Zhou Yu @ NJUPT</div>

   		
  </body>
</html>
