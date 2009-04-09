/**
 * This class tests the functionality of the Building class.
 */
package com.inverse2.test.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.inverse2.rendezvous.model.Building;
import com.inverse2.rendezvous.model.Floor;
import com.inverse2.rendezvous.model.LkCountryCode;

/**
 * @author steve
 *
 */
public class BuildingTest extends TestCase {
	
    SessionFactory 	sessionFactory;
    Session 		session;
    Transaction 	tx;
    Building		building;
    static int		buildingCount;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
        sessionFactory 	= new Configuration().configure().buildSessionFactory();
        session 		= sessionFactory.openSession();
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * Test that we can retrieve a building object with an id of 1.
	 */
	public void testBuildingGetById() {
		System.out.println("** Test building get by Id...");
		try {
	        tx = session.beginTransaction();
	        // Get single record
	        building = (Building) session.get(Building.class, 1);
	        assertEquals("Tete-A-Tete House", building.getBuildingName());
	        
	        // This building should have a Set of child Floor objects...
	        Set floors = building.getFloors();
	        assertNotNull(floors);
	        for (Iterator i = floors.iterator(); i.hasNext(); ) {
	        	Floor f = (Floor) i.next();
	        	System.out.println(">> Building floor -> " + f.getFloorName());
	        }
	        assertEquals(4, building.getFloors().size());
	        tx.commit();
		}
		catch (Exception ex) {
			fail("Exception getting building 1: " + ex.toString());
			tx.rollback();
		}
	}
	
	/**
	 * Test that we can retrieve the list of all buildings...
	 */
	public void testGetAllBuildingsList() {
		System.out.println("** Test get all buildings...");
        tx = session.beginTransaction();
        List buildingList = session.createQuery("from Building").list();
        assertNotNull(buildingList);
        buildingCount = buildingList.size();
        for (int i = 0; i < buildingCount; i++) {
            building = (Building) buildingList.get(i);
            System.out.println("Row " + (i + 1) + "> " + building.getBuildingName());
        }
        tx.commit();
	}

	/**
	 * Test that we can create a new building...
	 */
	public void testCreateBuilding() {
		System.out.println("** Test create building...");
		try {
	        tx = session.beginTransaction();
			String           buildingName  = "My New Building";
			SimpleDateFormat dateParser    = new SimpleDateFormat("yyyy-MM-dd");
			Building         otherBuilding;
			Set             buildingFloors;
			Floor			 floor;
			
			buildingFloors = new HashSet(0);
			floor = new Floor();
			floor.setFloorName("Atrium");
			buildingFloors.add(floor);
			floor = new Floor();
			floor.setFloorName("Balcony");
			buildingFloors.add(floor);
			
			building = new Building(new LkCountryCode("GBR"), buildingName, "88 Fleet Street", "", "", "London", "WC1 1BB", dateParser.parse("2001-01-01"), dateParser.parse("2100-01-01"), buildingFloors);
			session.save(building);
			tx.commit();
			assertEquals(buildingCount + 1, building.getBuildingId().intValue());
			
			/* Now retrieve the new building by id... */
	        otherBuilding = (Building) session.get(Building.class, buildingCount+1);
	        assertEquals(buildingName, otherBuilding.getBuildingName());
	        Set floors = otherBuilding.getFloors();
	        assertNotNull("The new building should have some floors.", floors);
	        assertEquals("Expected there to be two building floors", 2, floors.size());
		}
		catch (Exception ex) {
			fail("Exception saving new building: " + ex.toString());
			tx.rollback();
		}
	}
	
	/**
	 * Test that we can search for a building by name...
	 */
	public void testSearchBuildingByName() {
		System.out.println("** Test search for a buildingby name...");
		try {
	        tx = session.beginTransaction();
			Query query = session.createQuery("from Building where buildingName like ?");
			List  results;
			query.setString(0, "Rend%");
			results = query.list();
			assertNotNull(results);
			System.out.println("Rows returned = " + results.size());
	        for (int i = 0; i < results.size(); i++) {
	            building = (Building) results.get(i);
	            System.out.println("Row found " + (i + 1) + "> " + building.getBuildingName());
	        }
	        tx.commit();
		}
		catch (Exception ex) {
			fail("Exception querying buildings: " + ex.toString());
			tx.rollback();
		}
	}
	
	/**
	 * Test that if we create a building with an invalid country it fails...
	 */
	public void testInvalidCountryCode() {
		System.out.println("** Test invlaid country code...");
		try {
	        tx = session.beginTransaction();
			String           buildingName  = "My Invalid Building";
			SimpleDateFormat dateParser    = new SimpleDateFormat("yyyy-MM-dd");
			Building         otherBuilding;
			LkCountryCode    countryCode;
			
			countryCode = new LkCountryCode("ZZZ");
			building    = new Building(countryCode, buildingName, "99 Fleet Street", "", "", "London", "WC1 1BB", dateParser.parse("2001-01-01"), dateParser.parse("2100-01-01"), new HashSet(0));
			
			session.save(building);
			tx.commit();
			fail("No exception saving new building with an invalid country code.");
		}
		catch (Exception ex) {
			System.out.println("Expected exception: " + ex.toString());
			assertNotNull("Exception generated because of invalid country code", ex);
			tx.rollback();
		}
	}
	
}
