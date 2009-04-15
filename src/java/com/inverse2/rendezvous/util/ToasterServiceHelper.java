package com.inverse2.rendezvous.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.inverse2.ajaxtoaster.AjaxToaster;
import com.inverse2.ajaxtoaster.DBConnectionPool;
import com.inverse2.ajaxtoaster.interfaces.ServiceOperationInterface;
import com.inverse2.rendezvous.model.Building;
import com.inverse2.rendezvous.model.Floor;
import com.inverse2.rendezvous.model.LkCountryCode;
import com.inverse2.rendezvous.model.Room;

public class ToasterServiceHelper {
	
    private static final AjaxToaster toaster;

    static {
        try {
        	ClassLoader cl  = ToasterServiceHelper.class.getClassLoader();
        	URL         url = cl.getResource("services");
        	
        	System.out.println("url = " + url + " ==== " + url.getFile());
        	
        	toaster = new AjaxToaster();
        	toaster.setScriptDirectory(url.getFile());
        	toaster.init();
        	System.out.println("*** AJAX TOASTER INITIALISED!");
        }
        catch (Throwable ex) {
            System.err.println("*** AJAX TOASTER CREATION FAILED: " + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static final Object getEntity(String serviceName, Class clazz, String... params) {
    	try {
    		System.out.printf("*** Getting service [%s]\n", serviceName);
    		
	    	ServiceOperationInterface service = toaster.getServiceScript(serviceName);
	    	
	    	if (service == null) {
	    		System.out.printf("*** No service called [%s] found - returning null.\n", serviceName);
	    		return(null);
	    	}
	    	
	    	for (String param : params) {
	    		String[] p = param.split("=");
	    		if (p.length == 2) {
	    			System.out.printf("** setting param: [%s] = [%s]\n", p[0], p[1]);
	    			service.setParameter(p[0], p[1]);
	    		}
	    	}
	    	
	    	System.out.printf("Invoking the operation...\n");
	    	
	    	String xml = service.invokeOperation();
	    	
	    	System.out.printf("Operation returned [%s]\n", xml);
	    	System.out.printf("Converting to a POJO...\n");
	    	
	    	Object obj = xmlToPOJO(xml, clazz);
	    	
			System.out.printf("POJO created.\n");
			
			return(obj);
    	}
    	catch (Exception ex) {
    		System.out.printf("Exception getting entity: %s\n", ex.toString());
    		ex.printStackTrace();
    		return(null);
    	}
    }
    
    public static final Object putEntity(String serviceName, Class clazz, Object entity, String... params) {
    	try {
    		System.out.printf("*** Getting service [%s]\n", serviceName);
    		
	    	ServiceOperationInterface service = toaster.getServiceScript(serviceName);
	    	
	    	if (service == null) {
	    		System.out.printf("*** No service called [%s] found - returning null.\n", serviceName);
	    		return(null);
	    	}
	    	
	    	for (String param : params) {
	    		String[] p = param.split("=");
	    		if (p.length == 2) {
	    			System.out.printf("** setting param: [%s] = [%s]\n", p[0], p[1]);
	    			service.setParameter(p[0], p[1]);
	    		}
	    	}
	    	
			String xml = pojoToXML(entity, clazz);
			
			System.out.printf("POJO marshalled into [%s].\n", xml);
			
			service.setInputXML(xml);
			
	    	System.out.printf("Invoking the operation...\n");
	    	
	    	xml = service.invokeOperation();
	    	
	    	System.out.printf("Operation returned [%s]\n", xml);
	    	System.out.printf("Converting to a POJO...\n");
	    	
			Object returnEntity = xmlToPOJO(xml, clazz);
			
	    	System.out.printf("Converted ok.\n");
			
			return(returnEntity);
    	}
    	catch (Exception ex) {
    		System.out.printf("Exception saving entity: %s\n", ex.toString());
    		ex.printStackTrace();
    		return(null);
    	}
    }
    
    private static final Object xmlToPOJO(String xml, Class clazz) throws Exception {
    	StringReader reader  = new StringReader(xml);
		JAXBContext  context = JAXBContext.newInstance(clazz);
		Unmarshaller um      = context.createUnmarshaller();
		return(um.unmarshal(reader));
    }
    
    private static final String pojoToXML(Object pojo, Class clazz) throws Exception {
    	StringWriter writer  = new StringWriter();
		JAXBContext  context = JAXBContext.newInstance(clazz);
		Marshaller   m       = context.createMarshaller();
		m.marshal(pojo, writer);
		return(writer.toString());
    }
	
}
