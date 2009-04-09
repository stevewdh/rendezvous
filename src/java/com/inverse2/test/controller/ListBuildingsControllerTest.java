package com.inverse2.test.controller;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.springframework.web.servlet.ModelAndView;

import com.inverse2.rendezvous.controller.ListBuildingsController;

public class ListBuildingsControllerTest extends TestCase {

	public void testHandleRequest() {
		try {
			ListBuildingsController controller = new ListBuildingsController();
	        ModelAndView modelAndView = controller.handleRequest(null, null);		
	        assertEquals("Unexpected name for the view.", "listbuildings", modelAndView.getViewName());
	        Map model = modelAndView.getModel();
	        assertNotNull("No model object for the Building List view.", model);
	        List buildingList = (List) model.get("buildingsList");
	        assertNotNull("No building list object in the model.", buildingList);
		}
		catch (Exception ex) {
			fail("Exception thrown: " + ex.toString());
		}
	}

}
