package com.inverse2.rendezvous.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.inverse2.rendezvous.context.ApplicationContextManager;
import com.inverse2.rendezvous.databinding.MasterDetailServletRequestDataBinder;
import com.inverse2.rendezvous.model.LkUserPriviledgeCodeList;
import com.inverse2.rendezvous.model.LkUserStatusCodeList;
import com.inverse2.rendezvous.model.User;
import com.inverse2.rendezvous.util.ToasterServiceHelper;

public class EditUserController extends SimpleFormController {
	
	private ApplicationContextManager applicationContextManager;
	User     user;
	String   userIdParam;
	int      userId;
	
    public ApplicationContextManager getApplicationContextManager() {
        return applicationContextManager;
    }

    public void setApplicationContextManager(ApplicationContextManager applicationContextManager) {
        this.applicationContextManager = applicationContextManager;
    }
    
	public ServletRequestDataBinder createBinder(HttpServletRequest request, Object command) throws Exception {
		ServletRequestDataBinder b = new MasterDetailServletRequestDataBinder(command, getCommandName());
		prepareBinder(b);
		initBinder(request, b);
		return(b);
	}
	
	/**
	 * This method is called by the Spring infrastructure when a GET is 
	 * performed on the form.
	 */
	public Object formBackingObject(HttpServletRequest request) {
		
		/**
		 * See if a user Id has been passed into the form...
		 * If it has then return that specific user object,
		 * otherwise return a new empty user object.
		 */
		
		userIdParam = request.getParameter(ControllerConstants.USER_ID_PARAM);
		
		if (userIdParam != null && userIdParam.length() != 0) {
			/* Return a specific user for editing on the form... */
			try {
				userId = Integer.parseInt(userIdParam);
				user   = (User) ToasterServiceHelper.getEntity("user/getUserById", User.class, "userId="+userId);
			}
			catch (Exception ex) {
				System.err.println("Could not parse user Id [" + userIdParam + "]");
				user = new User();
			}
		}
		else {
			user = new User();
		}
		
		applicationContextManager.setContextItem(request, ControllerConstants.USER_ID_PARAM, userId);
		
		return(user);
	}
	
	/**
	 * This method can be used to register custom field editors...
	 */
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        //binder.registerCustomEditor(int.class, new MinutesPropertyEditor());
    }
	
	/**
	 * This method can be used to attach reference data to the model...
	 */
    public Map referenceData(HttpServletRequest request) throws Exception {
        HashMap model = new HashMap();
        model.put("priviledgeCodes", ToasterServiceHelper.getEntity("lookupData/getUserPriviledgeCodes", LkUserPriviledgeCodeList.class));
        model.put("statusCodes",     ToasterServiceHelper.getEntity("lookupData/getUserStatusCodes",     LkUserStatusCodeList.class));
        return(model);
    }
    
    /**
     * This is method is called when the validation has passed ok and the command
     * object is needed to be saved.  The method returns the view that will be shown
     * when the save is completed.
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
    	user = (User)ToasterServiceHelper.putEntity("user/saveUser", User.class, command);
    	
    	/**
    	 * If the user has just edited their own user information then refresh the logged in user details...
    	 */
    	User loggedInUser = applicationContextManager.getUser(request);
    	if (loggedInUser.getUserId() == user.getUserId()) {
    		applicationContextManager.setUser(request, user);
    	}
    	
    	return new ModelAndView(getSuccessView()+"?"+ControllerConstants.USER_ID_PARAM+"="+user.getUserId());
    }
	
}
