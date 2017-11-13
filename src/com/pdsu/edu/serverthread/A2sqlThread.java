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
        while (!this.isInterrupted()) {// �߳�δ�ж�ִ��ѭ��  
            try {
//              ------------------ ��ʼִ�� ---------------------------  
                System.out.println("____Socket Starting TIME:" + System.currentTimeMillis());  
                ServerSocket serverSocket = new ServerSocket(8888);
                System.out.println("��������������ȴ��ͻ�������..");
                C3P0ConnentionProvider ccp = C3P0ConnentionProvider.getInstance();
               
                while (true) {
                    Socket socket = serverSocket.accept();// ���������ܵ����׽��ֵ�����,����һ��Socket����
                    InputStream inputStream=socket.getInputStream();//�õ�һ�������������տͻ��˴��ݵ���Ϣ
                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//���Ч�ʣ����Լ��ֽ���תΪ�ַ���
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//���뻺����
                    String temp=null;
                    String info="";
                    while((temp=bufferedReader.readLine())!=null){
                        info+=temp;
                        System.out.println("�ѽ��յ��ͻ�������");
                        System.out.println("��ǰ�ͻ���ipΪ��"+socket.getInetAddress().getHostAddress());
                        A2sqlThread.arduinoIP=socket.getInetAddress().getHostAddress();
                        
                        String information = "\""+info+"\"";
                        String time ="\""+A2sqlThread.nowTime()+"\"";                   
                        try {
                        	 Connection conn = ccp.getConnection();  
                 			System.out.println("�������ݿ����ӽ���");
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
                    
                    OutputStream outputStream=socket.getOutputStream();//��ȡһ��������������˷�����Ϣ
                    PrintWriter printWriter=new PrintWriter(outputStream);//���������װ�ɴ�ӡ��
                    printWriter.print("��ã�������ѽ��յ�������Ϣ");
                    printWriter.flush();
                    socket.shutdownOutput();//�ر������
                    
                    
                    
                    //�ر����Ӧ����Դ
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