package com.inverse2.rendezvous.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.inverse2.rendezvous.context.ApplicationContextManager;
import com.inverse2.rendezvous.databinding.MasterDetailServletRequestDataBinder;
import com.inverse2.rendezvous.model.Room;
import com.inverse2.rendezvous.util.ToasterServiceHelper;

public class EditRoomController extends SimpleFormController {
	
	private ApplicationContextManager applicationContextManager;
	Room     room;
	String   roomIdParam;
	int      roomId;
	String   floorIdParam;
	int      floorId;
	
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
		
		roomIdParam  = request.getParameter(ControllerConstants.ROOM_ID_PARAM);
		floorIdParam = request.getParameter(ControllerConstants.FLOOR_ID_PARAM);
		
		try {
			floorId = Integer.parseInt(floorIdParam);
		}
		catch (Exception ex) {
			floorId = (Integer) applicationContextManager.getContextItem(request, ControllerConstants.FLOOR_ID_PARAM);
		}
		
		if (roomIdParam != null && roomIdParam.length() != 0) {
			/* Return a specific room for editing on the form... */
			try {
				roomId = Integer.parseInt(roomIdParam);
				room   = (Room) ToasterServiceHelper.getEntity("building/getBuildingRoom", Room.class, "roomId="+roomId);
			}
			catch (Exception ex) {
				System.err.println("Could not create room object [" + roomIdParam + "]");
				room = new Room();
				/* Link the room to a floor... */
				room.setFloorId(floorId);
			}
		}
		else {
			room = new Room();
			/* Link the room to a floor... */
			room.setFloorId(floorId);
		}
		
		saveIds(request);
		
		return(room);
	}
	
	private void saveIds(HttpServletRequest request) {
		applicationContextManager.setContextItem(request, ControllerConstants.FLOOR_ID_PARAM, floorId);
		applicationContextManager.setContextItem(request, ControllerConstants.ROOM_ID_PARAM,  roomId);
	}
	
    public ApplicationContextManager getApplicationContextManager() {
        return applicationContextManager;
    }

    public void setApplicationContextManager(ApplicationContextManager applicationContextManager) {
        this.applicationContextManager = applicationContextManager;
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
    	ToasterServiceHelper.putEntity("building/saveBuildingRoom", Room.class, command);
    	return new ModelAndView(getSuccessView() + "?"+ControllerConstants.FLOOR_ID_PARAM+"=" + floorId);
    }
}
