package com.inverse2.rendezvous.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.inverse2.rendezvous.context.ApplicationContextManager;

public class SignOutController implements Controller {

	private String                     signOutPage;
	private ApplicationContextManager applicationContextManager;
	
	public String getSignOutPage() {
		return(signOutPage);
	}

	public void setSignOutPage(String signOutPage) {
		this.signOutPage = signOutPage;
	}

    public ApplicationContextManager getApplicationContextManager() {
        return applicationContextManager;
    }

    public void setApplicationContextManager(ApplicationContextManager applicationContextManager) {
        this.applicationContextManager = applicationContextManager;
    }

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		applicationContextManager.setUser(request, null);
		return(new ModelAndView(signOutPage));
	}
	
	

}
