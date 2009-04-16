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
import com.inverse2.rendezvous.model.Building;
import com.inverse2.rendezvous.model.LkCountryCodeList;
import com.inverse2.rendezvous.util.ToasterServiceHelper;

public class EditBuildingController extends SimpleFormController {
	
	private ApplicationContextManager applicationContextManager;
	Building building;
	String   buildingIdParam;
	int      buildingId;
	
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
		 * See if a building Id has been passed into the form...
		 * If it has then return that specific building object,
		 * otherwise return a new empty building object.
		 */
		
		buildingIdParam = request.getParameter(ControllerConstants.BUILDING_ID_PARAM);
		
		if (buildingIdParam != null && buildingIdParam.length() != 0) {
			/* Return a specific building for editing on the form... */
			try {
				buildingId = Integer.parseInt(buildingIdParam);
				building   = (Building) ToasterServiceHelper.getEntity("building/getBuildingById", Building.class, "buildingId="+buildingId);
			}
			catch (Exception ex) {
				System.err.println("Could not parse building Id [" + buildingIdParam + "]");
				building = new Building();
			}
		}
		else {
			building = new Building();
		}
		
		applicationContextManager.setContextItem(request, ControllerConstants.BUILDING_ID_PARAM, buildingId);
		
		return(building);
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
        model.put("countryCodes", ToasterServiceHelper.getEntity("lookupData/getCountryCodes", LkCountryCodeList.class));
        return(model);
    }
    
    /**
     * This is method is called when the validation has passed ok and the command
     * object is needed to be saved.  The method returns the view that will be shown
     * when the save is completed.
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
    	building = (Building)ToasterServiceHelper.putEntity("building/saveBuilding", Building.class, command);
    	return new ModelAndView(getSuccessView()+"?"+ControllerConstants.BUILDING_ID_PARAM+"="+building.getBuildingId());
    }
	
}
