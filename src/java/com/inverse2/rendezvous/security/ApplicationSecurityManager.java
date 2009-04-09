package com.inverse2.rendezvous.security;

import javax.servlet.http.HttpServletRequest;

import com.inverse2.rendezvous.model.User;

public class ApplicationSecurityManager {

	public static final String USER = "user";
	
	public User getUser(HttpServletRequest request) {
		User user = (User) request.getSession(true).getAttribute(USER);
		return(user);
	}
	
	public void setUser(HttpServletRequest request, User user) {
		request.getSession(true).setAttribute(USER, user);
	}
	
}
