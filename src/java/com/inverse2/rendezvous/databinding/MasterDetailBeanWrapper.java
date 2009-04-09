package com.inverse2.rendezvous.databinding;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.MethodParameter;

public class MasterDetailBeanWrapper implements BeanWrapper {
	
	private Object			wrappedInstance;
	private boolean			extractOldValueForEditor;
	private BeanInfoCache	beanInfoCache;
	
	public PropertyDescriptor getPropertyDescriptor(String propertyName) throws BeansException {
		return(beanInfoCache == null ? null : beanInfoCache.getPropertyDescriptor(propertyName));
	}

	public PropertyDescriptor[] getPropertyDescriptors() {
		return(beanInfoCache == null ? null : beanInfoCache.getPropertyDescriptors());
	}

	public Class getWrappedClass() {
		return(wrappedInstance == null ? null : wrappedInstance.getClass());
	}

	public Object getWrappedInstance() {
		return(wrappedInstance == null ? null : wrappedInstance);
	}

	public void setWrappedInstance(Object o) {
		wrappedInstance = o;
		cacheBeanInfo(wrappedInstance);
	}

	public boolean isExtractOldValueForEditor() {
		return(extractOldValueForEditor);
	}

	public void setExtractOldValueForEditor(boolean b) {
		extractOldValueForEditor = b;
	}

	public Class getPropertyType(String propertyName) throws BeansException {
		return(beanInfoCache == null ? null : beanInfoCache.getPropertyType(propertyName));
	}

	public Object getPropertyValue(String propertyName) throws BeansException {
		System.out.println("getPropertyValue(" + propertyName + ")");
		return(beanInfoCache == null ? null : beanInfoCache.getPropertyValue("command." + propertyName));
	}

	public boolean isReadableProperty(String propertyName) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isWritableProperty(String propertyName) {
		// TODO Auto-generated method stub
		return true;
	}

	public void setPropertyValue(PropertyValue propertyValue) throws BeansException {
		// TODO Auto-generated method stub
		
	}

	public void setPropertyValue(String propertyName, Object propertyValue) throws BeansException {
		System.out.println("setProperty " + propertyName + " = " + propertyValue);
		if (propertyName.startsWith("command")) {
			Object o = beanInfoCache.setPropertyValue(propertyName, propertyValue);
			System.out.println("set result = " + o);
		}
	}

	public void setPropertyValues(Map propertyValues) throws BeansException {
		Iterator it = propertyValues.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object val = propertyValues.get(key);
			setPropertyValue(key, val);
		}
	}

	public void setPropertyValues(PropertyValues propertyValues) throws BeansException {
		PropertyValue[] pv = propertyValues.getPropertyValues();
		for (int i = 0; i < pv.length; i++) {
			setPropertyValue(pv[i].getName(), pv[i].getValue());
		}
	}

	public void setPropertyValues(PropertyValues propertyValues, boolean ignoreUnknown) throws BeansException {
		PropertyValue[] pv = propertyValues.getPropertyValues();
		for (int i = 0; i < pv.length; i++) {
			setPropertyValue(pv[i].getName(), pv[i].getValue());
		}
	}

	public void setPropertyValues(PropertyValues propertyValues, boolean ignoreUnknown, boolean ignoreInvalid) throws BeansException {
		PropertyValue[] pv = propertyValues.getPropertyValues();
		for (int i = 0; i < pv.length; i++) {
			setPropertyValue(pv[i].getName(), pv[i].getValue());
		}
	}

	public PropertyEditor findCustomEditor(Class requiredType, String propertyPath) {
		// TODO Auto-generated method stub
		return null;
	}

	public void registerCustomEditor(Class requiredType, PropertyEditor propertyEditor) {
		// TODO Auto-generated method stub
		
	}

	public void registerCustomEditor(Class requiredType, String propertyPath, PropertyEditor propertyEditor) {
		// TODO Auto-generated method stub
		
	}

	public Object convertIfNecessary(Object value, Class requiredType) throws TypeMismatchException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object convertIfNecessary(Object value, Class requiredType, MethodParameter methodParam) throws TypeMismatchException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This method caches the property information about a bean... it follows the object structure down
	 * from the top level. 
	 * @param bean Object to cache bean info about, or null to reset internal data structures.
	 */
	private void cacheBeanInfo(Object bean) {
		if (bean == null) {
			/* Reset internal data structures... */
			beanInfoCache = null;
			return;
		}
		System.out.println("***** Cache bean info...");
		beanInfoCache = new BeanInfoCache(bean);
	}
	
}
