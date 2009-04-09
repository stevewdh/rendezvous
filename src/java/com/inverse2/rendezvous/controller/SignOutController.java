package com.inverse2.rendezvous.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.inverse2.rendezvous.security.ApplicationSecurityManager;

public class SignOutController implements Controller {

	private String                     signOutPage;
	private ApplicationSecurityManager applicationSecurityManager;
	
	public String getSignOutPage() {
		return(signOutPage);
	}

	public void setSignOutPage(String signOutPage) {
		this.signOutPage = signOutPage;
	}

    public ApplicationSecurityManager getApplicationSecurityManager() {
        return applicationSecurityManager;
    }

    public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
        this.applicationSecurityManager = applicationSecurityManager;
    }

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		applicationSecurityManager.setUser(request, null);
		return(new ModelAndView(signOutPage));
	}
	
	

}
