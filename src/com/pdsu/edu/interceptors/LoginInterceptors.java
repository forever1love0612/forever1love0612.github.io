package com.pdsu.edu.interceptors;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.pdsu.edu.domain.User;

public class LoginInterceptors extends AbstractInterceptor{
	
	private static final long serialVersionUID = 1L;
	private String sessionName;
	private String excludeName;
	private List<String> list;
	
	public List<String> strlsit(String str) {
		String[] s = str.split(",");
		List<String> list = new ArrayList<String>();
		for (String ss : s) {
			list.add(ss.trim());
		}
		return list;
	}

	@Override
	public void init() {
		list = strlsit(excludeName);
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getProxy().getActionName();

		if (list.contains(actionName)) {
			// 请求的是合法
			return invocation.invoke();
		} else {
			// 查看session
			Map<String, Object> session = invocation.getInvocationContext().getSession();
			User user = (User) session.get("currUser");
			if (user == null) { 
				
			    return "loginvalidate";
			} else {
				return invocation.invoke();
			}
		}
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getExcludeName() {
		return excludeName;
	}

	public void setExcludeName(String excludeName) {
		this.excludeName = excludeName;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}




}