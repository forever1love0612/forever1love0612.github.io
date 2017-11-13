<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>历史通信数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">   
<style>
a:link,a:visited
{
font-weight:bold;
font-size:14px;
font-family:Verdana, Arial, Helvetica, sans-serif;
color:#FFFFFF;
background-color:#98bf21;
width:120px;
text-align:center;
padding:4px;
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
     <div align ="left" style="font-family:arial;">当前Arduino电路板IP地址为：<s:property value="ardIP"/></div>
    <center> 
    <h3>当前电路状态</h3> 
<img id="led1"  src="<%=request.getContextPath()%>/pictures/eg_bulboff.gif">
<img id="led2"  src="<%=request.getContextPath()%>/pictures/eg_bulboff.gif">
<img id="led3"  src="<%=request.getContextPath()%>/pictures/eg_bulboff.gif">
<img id="led4"  src="<%=request.getContextPath()%>/pictures/eg_bulboff.gif">
<img id="led5"  src="<%=request.getContextPath()%>/pictures/eg_bulboff.gif">
<br><br>

                 <table width="70%" border="1" id = "mytable2" >
            <tr>
            	<th>时间</th>
            	<th>LED1</th>
            	<th>LED2</th>
            	<th>LED3</th>
            	<th>LED4</th>
            	<th>LED5</th>
            </tr>
	        <s:iterator value="stateList">
	        	<tr>
	        		<td align="center"><s:property value="time"/> </td>
	        		<td align="center"><s:property value="led1"/> </td>
	        		<td align="center"><s:property value="led2"/> </td>
	        		<td align="center"><s:property value="led3"/> </td>
	        		<td align="center"><s:property value="led4"/> </td>
	        		<td align="center"><s:property value="led5"/> </td>
	        	</tr>
	        </s:iterator>
        </table>
          <br>
    </center>

    <div style="position:absolute; left:1000px">
       
    <s:a action="info_onlyGetState" namespace="/info">刷新</s:a> 
      <a href="<%=request.getContextPath()%>/welcome.jsp">返回管理页面</a>
      
      	       <s:form action="info_send" namespace="/info" method="post">
    		<s:textfield label="指令" name="info.infoSent"></s:textfield>
    		<s:submit value="发送"></s:submit>
    		</s:form>
   </div>

   		 <div class="botCenter">____________________________________</div>
         <div class="botCenter">designed by Zhou Yu @ NJUPT</div>
  </body>
     <script>  
  element1=document.getElementById('led1');
  led1State = document.getElementById("mytable2").rows[1].cells[1].innerHTML;
   if (led1State.match("off"))
  {
  element1.src="<%=request.getContextPath()%>/pictures/eg_bulboff.gif";
  }
else
  {
  element1.src="<%=request.getContextPath()%>/pictures/eg_bulbon.gif";
  }
  
    element2=document.getElementById('led2');
  led2State = document.getElementById("mytable2").rows[1].cells[2].innerHTML;
   if (led2State.match("off"))
  {
  element2.src="<%=request.getContextPath()%>/pictures/eg_bulboff.gif";
  }
else
  {
  element2.src="<%=request.getContextPath()%>/pictures/eg_bulbon.gif";
  }
  
      element3=document.getElementById('led3');
  led3State = document.getElementById("mytable2").rows[1].cells[3].innerHTML;
   if (led3State.match("off"))
  {
  element3.src="<%=request.getContextPath()%>/pictures/eg_bulboff.gif";
  }
else
  {
  element3.src="<%=request.getContextPath()%>/pictures/eg_bulbon.gif";
  }
  
        element4=document.getElementById('led4');
  led4State = document.getElementById("mytable2").rows[1].cells[4].innerHTML;
   if (led4State.match("off"))
  {
  element4.src="<%=request.getContextPath()%>/pictures/eg_bulboff.gif";
  }
else
  {
  element4.src="<%=request.getContextPath()%>/pictures/eg_bulbon.gif";
  }
  
  
          element5=document.getElementById('led5');
  led5State = document.getElementById("mytable2").rows[1].cells[5].innerHTML;
   if (led5State.match("off"))
  {
  element5.src="<%=request.getContextPath()%>/pictures/eg_bulboff.gif";
  }
else
  {
  element5.src="<%=request.getContextPath()%>/pictures/eg_bulbon.gif";
  }
  
   </script>
</html>
