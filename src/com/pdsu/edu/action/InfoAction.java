package com.pdsu.edu.action;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.pdsu.edu.domain.Info;
import com.pdsu.edu.domain.State;
import com.pdsu.edu.service.InfoService;
import com.pdsu.edu.service.StateService;

import C3P0provider.C3P0ConnentionProvider;

/**
 * 类说明：用户Action
 * 
 * @author 作者: LiuJunGuang
 * @version 创建时间：2012-3-25 下午03:29:52
 */
@Controller
@Scope("prototype")
public class InfoAction extends ActionSupport implements ServletResponseAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5875811544249837263L;
	/**
	 * 
	 */
	@Autowired
	private InfoService infoService;
	@Autowired
	private StateService stateService;
	private Info info;
	private State state;
	private List<Info> infoList;
	private List<State> stateList;
	public List<State> getStateList() {
		return stateList;
	}


	public void setStateList(List<State> stateList) {
		this.stateList = stateList;
	}
	private static String ardIP;
	private HttpServletResponse response;
    private static String arduino;
    private static String iferror;
    private String[] ledState;
	public String execute() throws Exception {
		return null;
	}


	public String add() {
		infoService.addInfo(info);
		return SUCCESS;
	}
	public void send() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		long sendTime = System.currentTimeMillis();
		String infoGot = null;
            //创建Socket对象
           try{
        	   iferror = null;
        	   ardIP = null;
        	   Socket socket=new Socket("192.168.4.1",9000);
       
           
            //根据输入输出流和服务端连接
            OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息
         
            InputStream inputStream=socket.getInputStream();//获取一个输入流，接收服务端的信息
            PrintWriter printWriter=new PrintWriter(outputStream);//将输出流包装成打印流
            printWriter.print(info.getInfoSent());
            printWriter.flush();
            
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//包装成字符流，提高效率
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//缓冲区
            
            String temp=null;//临时变量 
//            socket.shutdownInput();非常奇怪的事：使用socket.shutdownOutput也会对下面readLine()产生影响，返回null。
            temp=bufferedReader.readLine();
            infoGot=temp;
                InfoAction.ardIP=socket.getInetAddress().getHostAddress();
           
            //关闭相对应的资源
            
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
            socket.close();
           }catch (Exception e){
        	iferror = "there is an exception!";
        	PrintWriter out;
   		    response.setContentType("text/html;charset=UTF-8");
   		    response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码
   			try {
				out = response.getWriter();
				out.print("<script>alert('未能成功连接到电路板！')</script>");
	   			out.print("<script>window.location.href='http://192.168.4.2:8080/optionLog/info/info_queryAllInfo.action'</script>");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
           }
           //判断连接是否成功，是否建立数据库连接。
          if(iferror == null){
        	  long timecost = 0;
        	long getTime = System.currentTimeMillis();
		    timecost = getTime-sendTime;			 
            try { C3P0ConnentionProvider ccp = C3P0ConnentionProvider.getInstance();
				Connection conn = ccp.getConnection();
				System.out.println("已与数据库连接建立");
     			Statement stmt =conn.createStatement();			
    			String sqlString = "insert into info(sendtime,infosent,gettime,infogot,timecost) "+"values("+"\""+sendTime+"\""+","+"\""+info.getInfoSent()+"\""+","+"\""+getTime+"\""+","+"\""+infoGot+"\""+","+"\""+timecost+"\""+")";
    			System.out.println("sqlString:"+sqlString);
    			boolean insert = stmt.execute(sqlString);
    			System.out.println(Thread.currentThread().getName()+":"+insert);  
                stmt.close();
                conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            
          }
          
          //创建Socket对象
          try{
       	   iferror = null;
       	   ardIP = null;
       	   Socket socket=new Socket("192.168.4.1",9000);
      
          
           //根据输入输出流和服务端连接
           OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息
        
           InputStream inputStream=socket.getInputStream();//获取一个输入流，接收服务端的信息
           PrintWriter printWriter=new PrintWriter(outputStream);//将输出流包装成打印流
           printWriter.print("s");
           printWriter.flush();
           
           InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//包装成字符流，提高效率
           BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//缓冲区
           
           String temp=null;//临时变量 
//           socket.shutdownInput();非常奇怪的事：使用socket.shutdownOutput也会对下面readLine()产生影响，返回null。
           temp=bufferedReader.readLine();
           	arduino=temp;
               System.out.println("接收电路板发送信息："+arduino);
              ledState = temp.split(",");
               InfoAction.ardIP=socket.getInetAddress().getHostAddress();
          
           //关闭相对应的资源
           
           bufferedReader.close();
           inputStream.close();
           printWriter.close();
           outputStream.close();
           socket.close();
          }catch (Exception e){
       	iferror = "there is an exception!";
       	PrintWriter out;
   		    response.setContentType("text/html;charset=UTF-8");
   		    response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码
   			try {
   			out = response.getWriter();
   			out.print("<script>alert('未能成功连接到电路板！')</script>");
      			out.print("<script>window.location.href='http://192.168.4.2:8080/optionLog/info/info_queryAllInfo.action'</script>");
   		} catch (IOException e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		}
   			
          }
          //判断连接是否成功，是否建立数据库连接。
         if(iferror == null){
           try { C3P0ConnentionProvider ccp = C3P0ConnentionProvider.getInstance();
   			Connection conn = ccp.getConnection();
   			System.out.println("已与数据库连接建立");
    			Statement stmt =conn.createStatement();
    			System.out.println(df.format(System.currentTimeMillis())); 
   			String sqlString = "insert into state(time,LED1,LED2,LED3,LED4,LED5) "+"values("+"\""+df.format(System.currentTimeMillis())+"\""+","+"\""+ledState[0]+"\""+","+"\""+ledState[1]+"\""+","+"\""+ledState[2]+"\""+","+"\""+ledState[3]+"\""+","+"\""+ledState[4]+"\""+")";
   			System.out.println("sqlString:"+sqlString);
   			boolean insert = stmt.execute(sqlString);
   			System.out.println(Thread.currentThread().getName()+":"+insert);  
               stmt.close();
               conn.close();
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}  
           
           
       //发送成功后弹出提示框
   	try {
   		PrintWriter out;
   	    response.setContentType("text/html;charset=UTF-8");
   	    response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码
   		out = response.getWriter();
   		out.print("<script>alert('发送成功！')</script>");
   		out.print("<script>window.location.href='http://192.168.4.2:8080/optionLog/info/info_queryState.action'</script>");
           out.flush();
           out.close();
   	} catch (IOException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	}
         }
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String queryAllInfo() {
		infoList = infoService.findAllInfo();
		return "infoList";
		
	}
	public String queryState(){
		stateList = stateService.findAllState();
		stateList = stateList.subList(stateList.size()-1, stateList.size());
		return "state";
	}
	public String queryAllState(){
		stateList = stateService.findAllState();
		return "allState";
	}
	public void onlyGetState() {
		InfoAction.setArdIP(null);

		
        //创建Socket对象
       try{
    	   iferror = null;
    	   ardIP = null;
    	   Socket socket=new Socket("192.168.4.1",9000);
   
       
        //根据输入输出流和服务端连接
        OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息
     
        InputStream inputStream=socket.getInputStream();//获取一个输入流，接收服务端的信息
        PrintWriter printWriter=new PrintWriter(outputStream);//将输出流包装成打印流
        printWriter.print("s");
        printWriter.flush();
        
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//包装成字符流，提高效率
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//缓冲区
        
        String temp=null;//临时变量 
//        socket.shutdownInput();非常奇怪的事：使用socket.shutdownOutput也会对下面readLine()产生影响，返回null。
        temp=bufferedReader.readLine();
        	arduino=temp;
            System.out.println("接收电路板发送信息："+arduino);
           ledState = temp.split(",");
            InfoAction.ardIP=socket.getInetAddress().getHostAddress();
       
        //关闭相对应的资源
        
        bufferedReader.close();
        inputStream.close();
        printWriter.close();
        outputStream.close();
        socket.close();
       }catch (Exception e){
    	iferror = "there is an exception!";
    	PrintWriter out;
		    response.setContentType("text/html;charset=UTF-8");
		    response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码
			try {
			out = response.getWriter();
			out.print("<script>alert('未能成功连接到电路板！')</script>");
   			out.print("<script>window.location.href='http://127.0.0.1:8080/optionLog/welcome.jsp'</script>");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
       }
       //判断连接是否成功，是否建立数据库连接。
      if(iferror == null){
        try { C3P0ConnentionProvider ccp = C3P0ConnentionProvider.getInstance();
			Connection conn = ccp.getConnection();
			System.out.println("已与数据库连接建立");
 			Statement stmt =conn.createStatement();
 			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
 			System.out.println(df.format(System.currentTimeMillis())); 
			String sqlString = "insert into state(time,LED1,LED2,LED3,LED4,LED5) "+"values("+"\""+df.format(System.currentTimeMillis())+"\""+","+"\""+ledState[0]+"\""+","+"\""+ledState[1]+"\""+","+"\""+ledState[2]+"\""+","+"\""+ledState[3]+"\""+","+"\""+ledState[4]+"\""+")";
			System.out.println("sqlString:"+sqlString);
			boolean insert = stmt.execute(sqlString);
			System.out.println(Thread.currentThread().getName()+":"+insert);  
            stmt.close();
            conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
        
    //发送成功后弹出提示框
	try {
		PrintWriter out;
	    response.setContentType("text/html;charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");//防止弹出的信息出现乱码
		out = response.getWriter();
		out.print("<script>alert('连接成功！')</script>");
		out.print("<script>window.location.href='http://192.168.4.2:8080/optionLog/info/info_queryState.action'</script>");
        out.flush();
        out.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      }
	}
	public String getArdIP() {
		return ardIP;
	}
	
	public static void setArdIP(String ardIP) {
		InfoAction.ardIP=ardIP;
	}
	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}
	public List<Info> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<Info> infoList) {
		this.infoList = infoList;
	}
	public void setServletResponse(HttpServletResponse response) {
		   this.response = response;
		}
}
