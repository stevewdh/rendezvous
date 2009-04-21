package com.inverse2.rendezvous.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.inverse2.rendezvous.context.ApplicationContextManager;
import com.inverse2.rendezvous.model.User;
import com.inverse2.rendezvous.util.ToasterServiceHelper;

public class SignInController extends SimpleFormController {
	
	private ApplicationContextManager applicationContextManager;
	
	protected Object getFormBackingObject(HttpServletRequest request) throws Exception{
		return(new User());
	}
	
	/**
	 * If the user is already logged in then forward to the succes view...
	 */
	public ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors, Map controlModel) throws Exception {
		if (applicationContextManager.getUser(request) != null) {
			return(new ModelAndView(getSuccessView()));
		}
		
		return(super.showForm(request, response, errors, controlModel));
	}
	
	public void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) {
		if (errors.hasErrors() == true) {
			return;
		}
		
		String username = request.getParameter(ControllerConstants.USERNAME);
		String password = request.getParameter(ControllerConstants.PASSWORD);
		
		if (username == null || username.equals("")) {
			errors.reject("signin.error.username.empty");
		}
		
		if (password == null || password.equals("")) {
			errors.reject("signin.error.password.empty");
		}
		
		User user = null;
		try {
			user = (User) ToasterServiceHelper.getEntity("user/getUserByUsername", User.class, "username="+username);
			if (user.getPassword().equals(password) == false) {
				errors.reject("signin.error.invalid.password");
				return;
			}
			if (user.getStatus().equals("A") == false) {
				errors.reject("signin.error.inactive.user");
				return;
			}
		}
		catch (Exception ex) {
			System.out.printf("Exception selecting user [%s]\n", username);
			ex.printStackTrace();
			errors.reject("signin.error.error.selecting");
			return;
		}
		
		applicationContextManager.setUser(request, user);
	}
	
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        return new ModelAndView(getSuccessView());
    }
	
    public ApplicationContextManager getApplicationContextManager() {
        return applicationContextManager;
    }

    public void setApplicationContextManager(ApplicationContextManager applicationContextManager) {
        this.applicationContextManager = applicationContextManager;
    }

}
