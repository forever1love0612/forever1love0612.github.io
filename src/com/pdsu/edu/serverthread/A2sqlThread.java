package com.pdsu.edu.serverthread;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import C3P0provider.C3P0ConnentionProvider;

public class A2sqlThread extends Thread {  
	public static String arduinoIP;
	public static String nowTime() {
		  Calendar c = Calendar.getInstance();
		  c.setTimeInMillis(new Date().getTime());
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  return dateFormat.format(c.getTime());
		 }
    public void run() {  
        while (!this.isInterrupted()) {// 线程未中断执行循环  
            try {
//              ------------------ 开始执行 ---------------------------  
                System.out.println("____Socket Starting TIME:" + System.currentTimeMillis());  
                ServerSocket serverSocket = new ServerSocket(8888);
                System.out.println("服务端已启动，等待客户端连接..");
                C3P0ConnentionProvider ccp = C3P0ConnentionProvider.getInstance();
               
                while (true) {
                    Socket socket = serverSocket.accept();// 侦听并接受到此套接字的连接,返回一个Socket对象
                    InputStream inputStream=socket.getInputStream();//得到一个输入流，接收客户端传递的信息
                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//提高效率，将自己字节流转为字符流
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//加入缓冲区
                    String temp=null;
                    String info="";
                    while((temp=bufferedReader.readLine())!=null){
                        info+=temp;
                        System.out.println("已接收到客户端连接");
                        System.out.println("当前客户端ip为："+socket.getInetAddress().getHostAddress());
                        A2sqlThread.arduinoIP=socket.getInetAddress().getHostAddress();
                        
                        String information = "\""+info+"\"";
                        String time ="\""+A2sqlThread.nowTime()+"\"";                   
                        try {
                        	 Connection conn = ccp.getConnection();  
                 			System.out.println("已与数据库连接建立");
                 			Statement stmt =conn.createStatement();
                			String sqlString = "insert into info(information,time) "+"values("+information+","+time+")";
                			System.out.println("sqlString:"+sqlString);
                			boolean insert = stmt.execute("insert into info(information,time) "+"values("+information+","+time+")");
                			System.out.println(Thread.currentThread().getName()+":"+insert);  
                            stmt.close();
                            conn.close();
                         
                		} catch (SQLException e) {
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		}  
                      
                        
                        
                        
                        
                    }
                    
                    OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息
                    PrintWriter printWriter=new PrintWriter(outputStream);//将输出流包装成打印流
                    printWriter.print("你好，服务端已接收到您的信息");
                    printWriter.flush();
                    socket.shutdownOutput();//关闭输出流
                    
                    
                    
                    //关闭相对应的资源
                    printWriter.close();
                    outputStream.close();
                    bufferedReader.close();
                    inputStream.close();
                    socket.close();
                    
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
              

        }  
    }  
}  