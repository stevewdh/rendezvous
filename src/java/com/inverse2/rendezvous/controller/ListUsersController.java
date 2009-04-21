package com.inverse2.rendezvous.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.inverse2.rendezvous.model.UserList;
import com.inverse2.rendezvous.util.ToasterServiceHelper;

public class ListUsersController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    UserList userList = (UserList) ToasterServiceHelper.getEntity("user/getUserList", UserList.class);
		return(new ModelAndView("listusers", "userList", userList));
	}
	
}