package com.pdsu.edu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.pdsu.edu.serverthread.A2sqlThread;

/** 
 * Listener�ķ�ʽ�ں�ִ̨��һ�߳� 
 *  
 * @author Champion.Wong 
 *  
 */  
public class ArduinoListener implements ServletContextListener {  
    private A2sqlThread a2SqlThread;  
  
    public void contextDestroyed(ServletContextEvent e) {  
        if (a2SqlThread != null && a2SqlThread.isInterrupted()) {  
        	a2SqlThread.interrupt();  
        }  
    }  
  
    public void contextInitialized(ServletContextEvent e) {  
        String str = null;  
        if (str == null && a2SqlThread == null) {  
        	a2SqlThread = new A2sqlThread();  
        	a2SqlThread.start(); // servlet �����ĳ�ʼ��ʱ���� socket  
        }  
    }  
}  
