package com.inverse2.rendezvous.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.inverse2.rendezvous.model.User;
import com.inverse2.rendezvous.security.ApplicationSecurityManager;
import com.inverse2.rendezvous.util.HibernateUtil;

public class SignInController extends SimpleFormController {
	
	private ApplicationSecurityManager applicationSecurityManager;
	
	protected Object getFormBackingObject(HttpServletRequest request) throws Exception{
		return(new User());
	}
	
	/**
	 * If the user is already logged in then forward to the succes view...
	 */
	public ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors, Map controlModel) throws Exception {
		if (applicationSecurityManager.getUser(request) != null) {
			return(new ModelAndView(getSuccessView()));
		}
		
		return(super.showForm(request, response, errors, controlModel));
	}
	
	public void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) {
		if (errors.hasErrors() == true) {
			return;
		}
		
		/** TODO Check username/password... **/
		String username = request.getParameter(ControllerConstants.USERNAME);
		String password = request.getParameter(ControllerConstants.PASSWORD);
		
		if (username == null || username.equals("")) {
			errors.reject("signin.error.username.empty");
			return;
		}
		
		if (password == null || password.equals("")) {
			errors.reject("signin.error.password.empty");
			return;
		}
		
		// TODO Don't get the default user...
		User user = (User) HibernateUtil.getById(User.class, 1);
		
		applicationSecurityManager.setUser(request, user);
	}
	
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        return new ModelAndView(getSuccessView());
    }
	
    public ApplicationSecurityManager getApplicationSecurityManager() {
        return applicationSecurityManager;
    }

    public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
        this.applicationSecurityManager = applicationSecurityManager;
    }

}
