package com.inverse2.test.model;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.inverse2.rendezvous.model.Building;
import com.inverse2.rendezvous.model.LkCountryCode;

public class LkCountryCodeTest extends TestCase {

    SessionFactory 	sessionFactory;
    Session 		session;
    Transaction 	tx;
    
	protected void setUp() throws Exception {
		super.setUp();
        sessionFactory 	= new Configuration().configure().buildSessionFactory();
        session 		= sessionFactory.openSession();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		session.close();
		sessionFactory.close();
	}
	
	public void testGetCountryCodeList() {
		System.out.println("** Test get country code list...");
		try {
	        tx = session.beginTransaction();
	        List countryCodeList = session.createQuery("from LkCountryCode").list();
	        assertNotNull(countryCodeList);
	        int listSize = countryCodeList.size();
	        LkCountryCode cc;
	        for (int i = 0; i < listSize; i++) {
	            cc = (LkCountryCode) countryCodeList.get(i);
	            System.out.println("Row " + (i + 1) + "> " + cc.getCountryCode() + " = " + cc.getCountryName());
	        }
	        tx.commit();
		}
		catch (Exception ex) {
			fail("Exception getting Country Code list: " + ex.toString());
			tx.rollback();
		}
	}

}
