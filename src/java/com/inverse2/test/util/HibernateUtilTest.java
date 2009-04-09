package com.inverse2.test.util;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;

import com.inverse2.rendezvous.util.HibernateUtil;

public class HibernateUtilTest extends TestCase {

	/**
	 * Test that the get session factory does return something...
	 */
	public void testGetSessionFactory() {
		try {
			SessionFactory sessionFactory;
			sessionFactory = HibernateUtil.getSessionFactory();
			assertNotNull(sessionFactory);
		}
		catch (Exception ex) {
			fail("Exception getting a Hibernate session factory: " + ex.toString());
		}
	}

}
