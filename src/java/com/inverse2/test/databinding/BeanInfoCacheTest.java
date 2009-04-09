package com.inverse2.test.databinding;

import java.beans.PropertyDescriptor;
import java.util.Date;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.inverse2.rendezvous.databinding.BeanInfoCache;
import com.inverse2.rendezvous.model.Building;
import com.inverse2.rendezvous.model.Floor;

public class BeanInfoCacheTest extends TestCase {

    SessionFactory 	sessionFactory;
    Session 		session;
    Transaction 	tx;
    Building		building;

	protected void setUp() throws Exception {
		super.setUp();
        sessionFactory 	= new Configuration().configure().buildSessionFactory();
        session 		= sessionFactory.openSession();
        building = (Building) session.get(Building.class, 1);
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		session.close();
		sessionFactory.close();
	}

	public void testBeanInfoCacheObject() {
		System.out.println("** Test create BeanInfoCache...");
		try {
			BeanInfoCache bic = new BeanInfoCache(building);
		}
		catch (Exception ex) {
			fail("Unexpected exception: " + ex.toString());
		}
	}

	public void testBeanInfoCacheClassString() {
		System.out.println("** Test create BeanInfoCache (other constructor)...");
		try {
			BeanInfoCache bic = new BeanInfoCache(building, building.getClass(), "com.inverse2.rendezvous.model");
		}
		catch (Exception ex) {
			fail("Unexpected exception: " + ex.toString());
		}
	}

	public void testGetPropertyDescriptors() {
		System.out.println("** Test get property descriptors...");
		try {
			BeanInfoCache bic = new BeanInfoCache(building);
			PropertyDescriptor[] pdArray = bic.getPropertyDescriptors();
			assertNotNull(pdArray);
			assertTrue("Property descriptor array should not be empty", pdArray.length > 0);
			for (int i = 0; i < pdArray.length; i++) {
				System.out.println("Property: " + pdArray[i].getName());
			}
		}
		catch (Exception ex) {
			fail("Unexpected exception: " + ex.toString());
		}
	}

	public void testGetPropertyDescriptor() {
		System.out.println("** Test get property descriptor...");
		try {
			BeanInfoCache bic = new BeanInfoCache(building);
			
			PropertyDescriptor pd;
			
			pd = bic.getPropertyDescriptor("building.buildingName");
			assertNotNull("buildingName property should exist", pd);
			System.out.println("Property: " + pd.getName() + ", type: " + pd.getPropertyType().getCanonicalName());
			
			pd = bic.getPropertyDescriptor("building.activeFrom");
			assertNotNull("activeFrom property should exist", pd);
			System.out.println("Property: " + pd.getName() + ", type: " + pd.getPropertyType().getCanonicalName());
			
			pd = bic.getPropertyDescriptor("building.lkCountryCode");
			assertNotNull("lkCountryCode property should exist", pd);
			System.out.println("Property: " + pd.getName() + ", type: " + pd.getPropertyType().getCanonicalName());
			
			pd = bic.getPropertyDescriptor("building.lkCountryCode.countryCode");
			assertNotNull("countryCode property should exist", pd);
			System.out.println("Property: " + pd.getName() + ", type: " + pd.getPropertyType().getCanonicalName());
			
			pd = bic.getPropertyDescriptor("building.floors");
			assertNotNull("floors property should exist", pd);
			System.out.println("Property: " + pd.getName() + ", type: " + pd.getPropertyType().getCanonicalName());
			
			pd = bic.getPropertyDescriptor("building.floors.floorName");
			assertNotNull("floorName property should exist", pd);
			System.out.println("Property: " + pd.getName() + ", type: " + pd.getPropertyType().getCanonicalName());
			
		}
		catch (Exception ex) {
			fail("Unexpected exception: " + ex.toString());
		}
	}

	public void testGetPropertyType() {
		System.out.println("** Test get property type...");
		try {
			BeanInfoCache bic = new BeanInfoCache(building);
			Class c;
			
			c = bic.getPropertyType("building.buildingName");
			assertNotNull("buildingName property should exist", c);
			System.out.println("Class: " + c.getCanonicalName());
			
			c = bic.getPropertyType("building.activeFrom");
			assertNotNull("buildingName property should exist", c);
			System.out.println("Class: " + c.getCanonicalName());
			
			c = bic.getPropertyType("building.lkCountryCode");
			assertNotNull("buildingName property should exist", c);
			System.out.println("Class: " + c.getCanonicalName());
			
			c = bic.getPropertyType("building.lkCountryCode.countryCode");
			assertNotNull("buildingName property should exist", c);
			System.out.println("Class: " + c.getCanonicalName());
			
			c = bic.getPropertyType("building.floors");
			assertNotNull("buildingName property should exist", c);
			System.out.println("Class: " + c.getCanonicalName());
			
			c = bic.getPropertyType("building.floors.floorName");
			assertNotNull("buildingName property should exist", c);
			System.out.println("Class: " + c.getCanonicalName());
			
		}
		catch (Exception ex) {
			fail("Unexpected exception: " + ex.toString());
		}
	}

	public void testGetPropertyValue() {
		System.out.println("** Test get property type...");
		try {
			BeanInfoCache bic = new BeanInfoCache(building);
			Object o;
			
			o = bic.getPropertyValue("building.buildingName");
			assertNotNull("buildingName property should exist", o);
			System.out.println("Value of buildingName: " + o);
			
			o = bic.getPropertyValue("building.activeFrom");
			assertNotNull("buildingName property should exist", o);
			System.out.println("Value of activeFrom: " + o);
			
			o = bic.getPropertyValue("building.lkCountryCode");
			assertNotNull("buildingName property should exist", o);
			System.out.println("Value of lkCountryCode: " + o);
			
			o = bic.getPropertyValue("building.lkCountryCode.countryCode");
			assertNotNull("buildingName property should exist", o);
			System.out.println("Value of countryCode: " + o);
			
			o = bic.getPropertyValue("building.floors");
			assertNotNull("buildingName property should exist", o);
			System.out.println("Value of floors: " + o);
			
			o = bic.getPropertyValue("building.floors[0].floorName");
			assertNotNull("buildingName property should exist", o);
			System.out.println("Value of floors[0].floorName: " + o);
			
			o = bic.getPropertyValue("building.floors[1].floorName");
			assertNotNull("buildingName property should exist", o);
			System.out.println("Value of floors[1].floorName: " + o);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			fail("Unexpected exception: " + ex.toString());
		}
	}

	public void testSetPropertyValue() {
		System.out.println("** Test set property type...");
		try {
			BeanInfoCache bic = new BeanInfoCache(building);
			Object o;
			Object ret;
			
			o = bic.getPropertyValue("building.buildingName");
			System.out.println("Current value of buildingName: " + o);
			ret = bic.setPropertyValue("building.buildingName", "My House");
			assertNotNull("Return object should not be null", ret);
			o = bic.getPropertyValue("building.buildingName");
			System.out.println("New value of buildingName: " + o);
			
			o = bic.getPropertyValue("building.activeFrom");
			System.out.println("Current value of activeFrom: " + o);
			ret = bic.setPropertyValue("building.activeFrom", new Date());
			assertNotNull("Return object should not be null", ret);
			o = bic.getPropertyValue("building.activeFrom");
			System.out.println("New value of aciveFrom: " + o);
			
			o = bic.getPropertyValue("building.lkCountryCode.countryCode");
			System.out.println("Current value of countryCode: " + o);
			ret = bic.setPropertyValue("building.lkCountryCode.countryCode", "USA");
			assertNotNull("Return object should not be null", ret);
			o = bic.getPropertyValue("building.lkCountryCode.countryCode");
			System.out.println("New value of countryCode: " + o);
			
			o = bic.getPropertyValue("building.floors[0].floorName");
			System.out.println("Current value of floorName: " + o);
			ret = bic.setPropertyValue("building.floors[0].floorName", "Basement");
			assertNotNull("Return object should not be null", ret);
			o = bic.getPropertyValue("building.floors[0].floorName");
			System.out.println("New value of floorName: " + o);
			
			
			
			
			
			System.out.println(">>> floor[0] = " + bic.getPropertyValue("building.floors[0].floorName"));
			System.out.println(">>> floor[1] = " + bic.getPropertyValue("building.floors[1].floorName"));
			System.out.println(">>> floor[2] = " + bic.getPropertyValue("building.floors[2].floorName"));
			System.out.println(">>> floor[3] = " + bic.getPropertyValue("building.floors[3].floorName"));
			System.out.println(">>> floor[4] = " + bic.getPropertyValue("building.floors[4].floorName"));
			System.out.println(">>> floor[5] = " + bic.getPropertyValue("building.floors[5].floorName"));
			System.out.println(">>> floor[6] = " + bic.getPropertyValue("building.floors[6].floorName"));
			System.out.println(">>> floor[7] = " + bic.getPropertyValue("building.floors[7].floorName"));
			System.out.println(">>> floor[8] = " + bic.getPropertyValue("building.floors[8].floorName"));
			System.out.println(">>> floor[9] = " + bic.getPropertyValue("building.floors[9].floorName"));
			
			o = bic.getPropertyValue("building.floors[4].floorName");
			System.out.println("Current value of floorName: " + o);
			ret = bic.setPropertyValue("building.floors[6].floorName", "Pent-House-1");
			ret = bic.setPropertyValue("building.floors[7].floorName", "Pent-House-2");
			ret = bic.setPropertyValue("building.floors[8].floorName", "Pent-House-3");
			//assertNotNull("Return object should not be null", ret);
			o = bic.getPropertyValue("building.floors[4].floorName");
			System.out.println("New value of floorName: " + o);
			
			System.out.println(">>> floor[0] = " + bic.getPropertyValue("building.floors[0].floorName"));
			System.out.println(">>> floor[1] = " + bic.getPropertyValue("building.floors[1].floorName"));
			System.out.println(">>> floor[2] = " + bic.getPropertyValue("building.floors[2].floorName"));
			System.out.println(">>> floor[3] = " + bic.getPropertyValue("building.floors[3].floorName"));
			System.out.println(">>> floor[4] = " + bic.getPropertyValue("building.floors[4].floorName"));
			System.out.println(">>> floor[5] = " + bic.getPropertyValue("building.floors[5].floorName"));
			System.out.println(">>> floor[6] = " + bic.getPropertyValue("building.floors[6].floorName"));
			System.out.println(">>> floor[7] = " + bic.getPropertyValue("building.floors[7].floorName"));
			System.out.println(">>> floor[8] = " + bic.getPropertyValue("building.floors[8].floorName"));
			System.out.println(">>> floor[9] = " + bic.getPropertyValue("building.floors[9].floorName"));
			
			
			
			bic = new BeanInfoCache(new Building());
			ret = bic.setPropertyValue("building.lkCountryCode.countryCode", "USA");
			o = bic.getPropertyValue("building.lkCountryCode.countryCode");
			System.out.println("New value of countryCode: " + o);

			
			bic = new BeanInfoCache(new Floor());
			ret = bic.setPropertyValue("floor.displayOrder", "99");
			o = bic.getPropertyValue("floor.displayOrder");
			System.out.println("New value of displayOrder: " + o);

			
			
			/*
			
			o = bic.getPropertyValue("building.lkCountryCode.countryCode");
			assertNotNull("buildingName property should exist", o);
			System.out.println("Value of countryCode: " + o);
			
			o = bic.getPropertyValue("building.floors");
			assertNotNull("buildingName property should exist", o);
			System.out.println("Value of floors: " + o);
			
			o = bic.getPropertyValue("building.floors[0].floorName");
			assertNotNull("buildingName property should exist", o);
			System.out.println("Value of floors[0].floorName: " + o);
			
			o = bic.getPropertyValue("building.floors[1].floorName");
			assertNotNull("buildingName property should exist", o);
			System.out.println("Value of floors[1].floorName: " + o);
			
			*/
		}
		catch (Exception ex) {
			ex.printStackTrace();
			fail("Unexpected exception: " + ex.toString());
		}
	}

}
