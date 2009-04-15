package com.inverse2.rendezvous.context;

import javax.servlet.http.HttpServletRequest;

import com.inverse2.rendezvous.model.User;

public class ApplicationContextManager {

	public static final String USER = "user";
	
	public User getUser(HttpServletRequest request) {
		User user = (User) request.getSession(true).getAttribute(USER);
		return(user);
	}
	
	public void setUser(HttpServletRequest request, User user) {
		request.getSession(true).setAttribute(USER, user);
	}
	
	public void setContextItem(HttpServletRequest request, String itemName, Object item) {
		request.getSession(true).setAttribute(itemName, item);		
	}
	
	public Object getContextItem(HttpServletRequest request, String itemName) {
		return(request.getSession(true).getAttribute(itemName));
	}
	
}
