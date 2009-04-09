package com.inverse2.rendezvous.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.inverse2.rendezvous.security.ApplicationSecurityManager;

public class SimpleGenericController implements Controller {

	private String targetPage;
	
	public String getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(String welcomePage) {
		this.targetPage = welcomePage;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return(new ModelAndView(targetPage));
	}

}
