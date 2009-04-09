package com.inverse2.rendezvous.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * This code was taken almost in entirety from the Hibernate 3.1 
 * reference manual. The package name and formatting has been 
 * changed only.
 */

public class HibernateUtil {
	
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * This method returns the Hibernate session factory.
     * @return
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    /**
     * This method allows you to query Hibernate objects.
     * @param query	A query
     * @return		A list containing the results of the query.
     */
    public static List getListFromQuery(String query) {
	    SessionFactory 	sessionFactory;
	    Session 		session;
	    Transaction 	tx;
	    List			list;
	    
	    sessionFactory 	= getSessionFactory();
        session 		= sessionFactory.openSession();
        tx				= session.beginTransaction();
        list			= session.createQuery(query).list();
        
        tx.commit();
        session.close();
        
        return(list);
    }
    
    public static Object getById(Class clazz, Serializable id) {
	    SessionFactory 	sessionFactory;
	    Session 		session;
	    Transaction 	tx;
	    Object			obj;
	    
	    sessionFactory 	= getSessionFactory();
        session 		= sessionFactory.openSession();
        tx				= session.beginTransaction();
    	obj				= session.get(clazz, id);
    	
    	tx.commit();
    	session.close();
    	
    	return(obj);
    }
    
    public static void save(Object obj) {
	    SessionFactory 	sessionFactory;
	    Session 		session;
	    Transaction 	tx;
	    
	    sessionFactory 	= getSessionFactory();
        session 		= sessionFactory.openSession();
        tx				= session.beginTransaction();
    	
        session.saveOrUpdate(obj);
    	tx.commit();
    	session.close();
    }

}
