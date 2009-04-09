package com.inverse2.rendezvous.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.inverse2.rendezvous.databinding.MasterDetailServletRequestDataBinder;
import com.inverse2.rendezvous.model.Building;
import com.inverse2.rendezvous.model.Floor;
import com.inverse2.rendezvous.util.HibernateUtil;

public class EditFloorController extends SimpleFormController {
	
	Floor    floor;
	String   floorIdParam;
	int      floorId;
	String   buildingIdParam;
	int      buildingId;
	
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
		 * See if a floor Id has been passed into the form...
		 * If it has then return that specific floor object,
		 * otherwise return a new empty floor object.
		 */
		
		floorIdParam    = request.getParameter(ControllerConstants.FLOOR_ID_PARAM);
		buildingIdParam = request.getParameter(ControllerConstants.BUILDING_ID_PARAM);
		
		
		/* TODO Should not return a floor unless a valid building Id is passed in... */
		try {
			buildingId = Integer.parseInt(buildingIdParam);
		}
		catch (Exception ex) {
			buildingId = -1;
		}
		
		if (floorIdParam != null && floorIdParam.length() != 0) {
			/* Return a specific floor for editing on the form... */
			try {
				floorId = Integer.parseInt(floorIdParam);
				floor   = (Floor) HibernateUtil.getById(Floor.class, floorId);
			}
			catch (Exception ex) {
				System.err.println("Could not create floor object [" + floorIdParam + "]");
				floor = new Floor();
				/* Link the floor to a building... */
				floor.setBuilding((Building)HibernateUtil.getById(Building.class, buildingId));
			}
		}
		else {
			floor = new Floor();
			/* Link the floor to a building... */
			floor.setBuilding((Building)HibernateUtil.getById(Building.class, buildingId));
		}
		
		return(floor);
	}
	
	/**
	 * This method can be used to register custom field editors...
	 */
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        //binder.registerCustomEditor(int.class, new MinutesPropertyEditor());
    }
	
    /**
     * This is method is called when the validation has passed ok and the command
     * object is needed to be saved.  The method returns the view that will be shown
     * when the save is completed.
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
    	HibernateUtil.save(command);
    	return new ModelAndView(getSuccessView() + "?"+ControllerConstants.BUILDING_ID_PARAM+"=" + buildingId + "&" + ControllerConstants.FLOOR_ID_PARAM+"=" + floorId);
    }
}
