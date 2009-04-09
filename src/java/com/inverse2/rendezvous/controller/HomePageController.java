package com.inverse2.rendezvous.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.inverse2.rendezvous.security.ApplicationSecurityManager;

public class HomePageController implements Controller {
	
	private ApplicationSecurityManager applicationSecurityManager;
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return(new ModelAndView("homepage", "user", applicationSecurityManager.getUser(request)));
	}

    public ApplicationSecurityManager getApplicationSecurityManager() {
        return applicationSecurityManager;
    }

    public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
        this.applicationSecurityManager = applicationSecurityManager;
    }

}
