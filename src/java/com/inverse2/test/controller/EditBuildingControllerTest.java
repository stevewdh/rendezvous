package com.inverse2.test.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;

import com.inverse2.rendezvous.controller.ControllerConstants;
import com.inverse2.rendezvous.controller.EditBuildingController;
import com.inverse2.rendezvous.model.Building;
import com.inverse2.rendezvous.model.LkCountryCode;

public class EditBuildingControllerTest extends TestCase {
	
	EditBuildingController editBuildingController;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		editBuildingController = new EditBuildingController();
	}
	
	public void testFormBackingObjectNoBuildingIdParam() {
		System.out.println("** Test get form backing object with no building Id parameter...");
		try {
			HttpServletRequest request = new MockHttpServletRequest();
			Object             obj     = editBuildingController.formBackingObject(request);
			assertEquals("Form backing objec expected to be a Building", true, obj instanceof Building);
			Building           b = (Building) obj;
			assertEquals("Building name expected to be null", true, b.getBuildingName() == null);
		}
		catch (Exception ex) {
			fail("Exception thrown: " + ex.toString());
		}
	}

	public void testFormBackingObjectBuildingIdParamOf1() {
		System.out.println("** Test get form backing object with building Id parameter of 1...");
		try {
			MockHttpServletRequest request = new MockHttpServletRequest();
			request.addParameter(ControllerConstants.BUILDING_ID_PARAM, "1");
			Object             obj     = editBuildingController.formBackingObject(request);
			assertEquals("Form backing objec expected to be a Building", true, obj instanceof Building);
			Building           b = (Building) obj;
			String   expectedBuildingName = "Tete-A-Tete House";
			assertEquals("Unexpected building name", expectedBuildingName, b.getBuildingName());
		}
		catch (Exception ex) {
			fail("Exception thrown: " + ex.toString());
		}
	}

	public void testFormBackingObjectBuildingIdParamInvalid() {
		System.out.println("** Test get form backing object with building Id parameter invalid...");
		try {
			MockHttpServletRequest request = new MockHttpServletRequest();
			request.addParameter(ControllerConstants.BUILDING_ID_PARAM, "xx");
			Object             obj     = editBuildingController.formBackingObject(request);
			assertEquals("Form backing objec expected to be a Building", true, obj instanceof Building);
			Building           b = (Building) obj;
			assertEquals("Building name expected to be null", true, b.getBuildingName() == null);
		}
		catch (Exception ex) {
			fail("Exception thrown: " + ex.toString());
		}
	}

	public void testReferenceData() {
		System.out.println("** Test reference data method...");
		try {
			MockHttpServletRequest request = new MockHttpServletRequest();
			Map                    model   = editBuildingController.referenceData(request);
			assertNotNull("Model object not expected to be null", model);
			Object obj = model.get("countryCodes");
			assertNotNull("Model should contain entry for country codes", obj);
			assertTrue("Country codes should be an instance of a List", obj instanceof List);
			assertTrue("Country codes list should not be emtpy", ((List)obj).size() != 0);
			// Output the country codes...
			for (Iterator i = ((List)obj).iterator(); i.hasNext(); ) {
				LkCountryCode cc = (LkCountryCode) i.next();
				System.out.println("Country Code >> " + cc.getCountryCode() + " - " + cc.getCountryName());
			}
		}
		catch (Exception ex) {
			fail("Unexpected exception: " + ex.toString());
		}
	}

	public void testCreateBinder() {
		fail("Not yet implemented");
	}
	
	public void testOnSubmit() {
		fail("Not yet implemented");
	}

}
