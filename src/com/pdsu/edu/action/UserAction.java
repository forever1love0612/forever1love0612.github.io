package com.pdsu.edu.action;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Constants;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pdsu.edu.domain.User;
import com.pdsu.edu.service.UserService;

/**
 * 类说明：用户Action
 * 
 * @author 作者: LiuJunGuang
 * @version 创建时间：2012-3-25 下午03:29:52
 */
@Controller
@Scope("prototype")
public class UserAction extends ActionSupport implements ServletResponseAware, SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7376612964543275456L;
	@Autowired
	private UserService userService;
	private User user;
	private List<User> userList;
    private HttpServletResponse response;  
    private Map<String, Object> session;  
	public String execute() throws Exception {
		return null;
	}

	public String login() {
		if (user != null) {
			User user2 = userService.login(user);
			if (user2 != null) {
				session=ActionContext.getContext().getSession(); 
				session.put("currUser", user2);  
				return "welcome";
			}
		}
		this.addFieldError("user.username", "用户名或密码错误!");
		return INPUT;
	}
	public String logout(){
		if( ActionContext.getContext().getSession()!= null){
			ActionContext ac = ActionContext.getContext();
			Map session = ac.getSession();
			session.clear();
			return INPUT;
		}
		return "loginvalidate";
	}

	public String addUI() {
		return "addUser";
	}

	public String updateUI() {
		user = userService.findUserById(user.getId());
		return "updateUser";
	}

	public String add() {
		userService.addUser(user);
		return SUCCESS;
	}

	public String delete() {
		userService.deleteUser(user.getId());
		return SUCCESS;
	}

	public String update() {
		userService.updateUser(user);
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String queryAllUser() {
		userList = userService.findAllUser();
		return "userList";
	}
	
	public String querybyID(){
//		userList=null;
		userList=new ArrayList();
		if(userService.findUserById(user.getId())!=null){
		userList.add(userService.findUserById(user.getId()));
		}
		return "userList";
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}






}
