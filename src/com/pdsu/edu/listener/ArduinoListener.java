package com.pdsu.edu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.pdsu.edu.serverthread.A2sqlThread;

/** 
 * Listener的方式在后台执行一线程 
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
        	a2SqlThread.start(); // servlet 上下文初始化时启动 socket  
        }  
    }  
}  
