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

.botCenter{width:100%; height:35px; line-height:35px; background:#ccc; position:fixed; bottom:25px; left:0px; font-size:14px; color:#000; text-align:center;}

</style>
  </head>
  
  <body>
   <img src="<%=request.getContextPath()%>/pictures/title.jpg" />
    <center>  
        	<h3>历史状态信息列表</h3>
<a id="btn0"></a>
<a  href="#" id="btn1" onclick = "firstPage()">首页</a>
<a  href="#" id="btn2" onclick = "frontPage()">上一页</a>
<a  href="#" id="btn3" onclick = "nextPage()">下一页</a>
<a  href="#" id="btn4" onclick = "lastPage()">尾页</a> 
<a>转到 </a>
<input id="changePage" type="text" size="1" maxlength="4"/>
<a>页 </a>
<a  href="#" id="btn5" onclick = "changePage()">跳转</a>
                 <table width="70%" border="1" id = "mytable" >
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
<br><br>
    </center>

    <div style="position:absolute; left:1050px">  
      <a href="<%=request.getContextPath()%>/welcome.jsp">返回管理页面</a>
   </div>
       <div class="botCenter">____________________________________</div>
       <div class="botCenter">designed by Zhou Yu @ NJUPT</div>
   <script>
            var pageSize = 10;    //每页显示的记录条数
             var curPage=0;        //当前页
             var lastPage;        //最后页
             var direct=0;        //方向
            var len;            //总行数
            var page;            //总页数
            var begin;
            var end;

                len = document.getElementById("mytable").rows.length - 1;    // 求这个表的总行数，剔除第一行介绍
                page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//根据记录条数，计算页数
                // alert("page==="+page);
                curPage=1;    // 设置当前为第一页
                displayPage(1);//显示第一页

                document.getElementById("btn0").innerHTML="当前 " + curPage + "/" + page + " 页    每页 ";    // 显示当前多少页
                document.getElementById("sjzl").innerHTML="数据总量 " + len + "";        // 显示数据量
                document.getElementById("pageSize").value = pageSize;
                

                function firstPage(){    // 首页
                    curPage=1;
                    direct = 0;
                    displayPage();
                };
                function frontPage(){    // 上一页
                    direct=-1;
                    displayPage();
                };
                function nextPage(){    // 下一页
                    direct=1;
                    displayPage();
                };
               function lastPage(){    // 尾页
                    curPage=page;
                    direct = 0;
                    displayPage();
                };
               function changePage(){    // 转页
                    curPage=document.getElementById("changePage").value * 1;
                    if (!/^[1-9]\d*$/.test(curPage)) {
                        alert("请输入正整数");
                        return ;
                    }
                    if (curPage > page) {
                        alert("超出数据页面");
                        return ;
                    }
                    direct = 0;
                    displayPage();
                };

                
                $("#pageSizeSet").click(function setPageSize(){    // 设置每页显示多少条记录
                    pageSize = document.getElementById("pageSize").value;    //每页显示的记录条数
                    if (!/^[1-9]\d*$/.test(pageSize)) {
                        alert("请输入正整数");
                        return ;
                    }
                    len =$("#mytable tr").length - 1;
                    page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//根据记录条数，计算页数
                    curPage=1;        //当前页
                     direct=0;        //方向
                     firstPage();
                });
            

            function displayPage(){
                if(curPage <=1 && direct==-1){
                    direct=0;
                    alert("已经是第一页了");
                    return;
                } else if (curPage >= page && direct==1) {
                    direct=0;
                    alert("已经是最后一页了");
                    return ;
                }

                lastPage = curPage;

                // 修复当len=1时，curPage计算得0的bug
                if (len > pageSize) {
                    curPage = ((curPage + direct + len) % len);
                } else {
                    curPage = 1;
                }

                
                document.getElementById("btn0").innerHTML="当前 " + curPage + "/" + page + " 页    每页 ";        // 显示当前多少页

                begin=(curPage-1)*pageSize+1 ;// 起始记录号
                end = begin + 1*pageSize - 1;    // 末尾记录号

                
                if(end > len ) end=len;
                for(var i=0; i<document.getElementById("mytable").rows.length ; i++){       
                         if(i>=begin && i<=end || i==0){
                          document.getElementById("mytable").rows[i].style.display = "table-row";
                         }else{
                          document.getElementById("mytable").rows[i].style.display = "none";
                         }
                        
                }               
             }
    </script>
   		
  </body>
</html>
