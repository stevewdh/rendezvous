package com.inverse2.rendezvous.databinding;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.inverse2.rendezvous.model.Floor;

public class BeanInfoCache {
	
	private boolean								valid;
	private Object								bean;
	private BeanInfo							beanInfo;
	private Map<String,PropertyCache>			propertyCacheMap;
	private String								beanPackage;
	
	private static Map<String,BeanInfoCache>	beanTypeCacheMap;
	
	public static void addTypeToCache(String beanClassName, BeanInfoCache bic) {
		if (beanTypeCacheMap == null) {
			beanTypeCacheMap = new HashMap<String, BeanInfoCache>();
		}
		beanTypeCacheMap.put(beanClassName, bic);
	}
	
	public static BeanInfoCache getTypeFromCache(String typeName) {
		if (beanTypeCacheMap == null) {
			beanTypeCacheMap = new HashMap<String, BeanInfoCache>();
		}
		return(beanTypeCacheMap.get(typeName));
	}
	
	public BeanInfoCache(Object bean) {
		this(bean, bean.getClass(), bean.getClass().getPackage().getName());
	}
	
	public BeanInfoCache(Object bean, Class beanClass, String beanPackage) {
		this.valid = true;
		this.bean  = bean;
		propertyCacheMap = new HashMap<String,PropertyCache>();
		/**
		 * If the bean class has already been cache in the global cache then don't do it again...
		 */
		if (getTypeFromCache(beanClass.getCanonicalName()) != null) {
			return;
		}
		
		try {
			this.beanInfo    = Introspector.getBeanInfo(beanClass);
			this.beanPackage = beanPackage;
			
			/**
			 * Hook this bean info cache into the shared global cache...
			 */
			String beanClassName = beanClass.getCanonicalName();
			addTypeToCache(beanClassName, this);
			
			/**
			 * Cache information about the properties of this bean in a map.
			 * The map is keyed on the property name for easy access.
			 */
			PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < pd.length; i++) {
				this.propertyCacheMap.put(pd[i].getName(), new PropertyCache(bean, pd[i], beanPackage));
			}
		}
		catch (Exception ex) {
			System.out.println("Exception caching bean info: " + ex.toString());
			ex.printStackTrace();
			valid = false;
		}
	}
	
	public BeanInfo getBeanInfo() {
		return(beanInfo);
	}
	
	public Object getBean() {
		return(bean);
	}
	
	public PropertyDescriptor[] getPropertyDescriptors() {
		if (valid == false) {
			return(null);
		}
		BeanInfoCache bic = getTypeFromCache(bean.getClass().getCanonicalName());
		return(bic == null ? null : bic.getBeanInfo().getPropertyDescriptors());
	}
	
	public PropertyDescriptor getPropertyDescriptor(String propertyPath) {
		if (valid == false) {
			return(null);
		}
		PropertyCache p = findCachedProperty(bean.getClass().getCanonicalName(), propertyPath);
		return(p == null ? null : p.getPropertyDescriptor());
	}
	
	public Class getPropertyType(String propertyPath) {
		if (valid == false) {
			return(null);
		}
		PropertyCache p = findCachedProperty(bean.getClass().getCanonicalName(), propertyPath);
		return(p == null ? null : p.getPropertyType());
	}
	
	public Object getPropertyValue(String propertyPath) {
		return(getPropertyValue(bean, propertyPath));
	}
	
	private Object getPropertyValue(Object obj, String propertyPath) {
		if (valid == false) {
			return(null);
		}
		PropertyPathTokens toks      = new PropertyPathTokens(propertyPath);
		PropertyCache      prop      = null;
		String             className = obj.getClass().getCanonicalName();
		BeanInfoCache      bic       = getTypeFromCache(className);
		
		if (bic == null) {
			return(null);
		}
		
		prop = bic.getPropertyCacheMap().get(toks.getNextElement());
		
		if (prop == null) {
			/**
			 * Cannot find property
			 */
			return(null);
		}
		
		obj = prop.getPropertyValue(obj, toks.getNextIndex());
		
		if (toks.hasNext() == false || obj == null) {
			return(obj);
		}
		
		/* More levels to descend... */
		className = obj.getClass().getCanonicalName();
		bic = getTypeFromCache(className);
		
		if (bic == null) {
			return(null);
		}
		
		return(bic.getPropertyValue(obj, toks.remainingTokensToString()));
	}
	
	public Object setPropertyValue(String propertyPath, Object value) {
		if (valid == false) {
			return(null);
		}
		/**
		 * Make sure that the propertyPath specifies a valid property before we try to write to it 
		 */
		if (getPropertyType(propertyPath) == null) {
			return(null);
		}
		
		return(setPropertyValue(propertyPath, value, bean));
	}
	
	private Object setPropertyValue(String propertyPath, Object value, Object parent) {
		if (valid == false) {
			return(null);
		}
		
		Object             obj       = null;
		PropertyPathTokens toks      = new PropertyPathTokens(propertyPath);
		PropertyCache      prop      = null;
		String             className = parent.getClass().getCanonicalName();
		BeanInfoCache      bic       = getTypeFromCache(className);
		
		if (bic == null) {
			return(null);
		}
		
		prop = bic.getPropertyCacheMap().get(toks.getNextElement());
		
		if (prop == null) {
			/**
			 * Cannot find property
			 */
			return(null);
		}
		
		obj = prop.getPropertyValue(parent, toks.getNextIndex(), true);
		
		if (toks.hasNext() == false) {
			prop.setPropertyValue(parent, value);
			return(value);
		}
		
		/* More levels to descend... */
		className = obj.getClass().getCanonicalName();
		bic = getTypeFromCache(className);
		
		if (bic == null) {
			return(null);
		}
		
		return(bic.setPropertyValue(toks.remainingTokensToString(), value, obj));
	}
	
	Map<String,PropertyCache> getPropertyCacheMap() {
		return(propertyCacheMap);
	}
	
	private PropertyCache findCachedProperty(String className, String propertyPath) {
		PropertyPathTokens toks = new PropertyPathTokens(propertyPath);
		BeanInfoCache      bic  = getTypeFromCache(className);
		
		if (bic == null) {
			return(null);
		}
		
		PropertyCache      prop = bic.getPropertyCacheMap().get(toks.getNextElement());
		if (prop == null) {
			/**
			 * Cannot find property!
			 */
			return(null);
		}
		if (toks.hasNext()) {
			String propClassName = prop.getCanonicalClassName();
			bic = getTypeFromCache(propClassName);
			
			if (bic == null) {
				return(null);
			}
			
			return(bic.findCachedProperty(propClassName, toks.remainingTokensToString()));
		}
		return(prop);
	}
	
}

class PropertyCache {
	
	private PropertyDescriptor	propertyDescriptor;
	private boolean 			javaClass         = false;
	private boolean 			collectionClass   = false;
	private boolean				containerProperty = false;
	private String				canonicalClassName;
	
	public PropertyCache(Object rootBean, PropertyDescriptor pd, String beanPackage) {
		this.propertyDescriptor	= pd;
		
		/**
		 * If the property is a container for a child object or array of child objects then
		 * cache information about that also...
		 * We will decide this using the following algorithm;
		 *     -> if class name starts with 'java.lang.' then it is a primitive.
		 *     -> if class is 'java.util.Date' then it is a primitive.
		 *     -> if class implements a Collection interface then it is a container.
		 *     -> else it is a singleton container object
		 */
		String canonicalName = propertyDescriptor.getPropertyType().getCanonicalName();
		
		canonicalClassName = canonicalName;
		
		if (javaClass == false) {
			javaClass = canonicalName.startsWith("java.lang.");
		}
		if (javaClass == false) {
			javaClass = canonicalName.equals("java.util.Date");
		}
		
		Class[] interfaces = getPropertyType().getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			if (interfaces[i].getName().equals("java.util.Collection")) {
				collectionClass = true;
				break;
			}
		}
		
		/**
		 * If this property is a singleton container class then cache bean info for the container
		 */
		if (javaClass == false && collectionClass == false) {
			/* Create the bean info cache.. it will hook itself into the shared cache... */
			new BeanInfoCache(rootBean, getPropertyType(), beanPackage);
			containerProperty = true;
		}
		
		/**
		 * If this property is a collection class then we need to work out what class is contained
		 * in the collection and cache bean info for this class...
		 */
		if (collectionClass == true) {
			/**
			 * Class name is the bean package plus the property name (first character upper case and
			 * remove last character if it is an 's'... e.g. floors --> Floor)
			 */
			String className = propertyDescriptor.getName();
			className = className.substring(0, 1).toUpperCase() + className.substring(1);
			if (className.endsWith("s")) {
				className = className.substring(0, className.length()-1);
			}
			className = beanPackage + "." + className;
			
			try {
				Class  c = Class.forName(className);
				/* Create the bean info cache.. it will hook itself into the shared cache... */
				new BeanInfoCache(rootBean, c, beanPackage);
				containerProperty = true;
				canonicalClassName = className;
			}
			catch (Exception x) {
				
			}
			
		}
		
	}
	
	public String getCanonicalClassName() {
		return(canonicalClassName);
	}
	
	public boolean isContainerProperty() {
		return(containerProperty);
	}
	
	public PropertyDescriptor getPropertyDescriptor() {
		return(propertyDescriptor);
	}
	
	public Class getPropertyType() {
		return(propertyDescriptor.getPropertyType());
	}
	
	public Object getPropertyValue(Object obj, String index) {
		return(getPropertyValue(obj, index, false));		
	}
	
	public Object getPropertyValue(Object obj, String index, boolean createIfNotExists) {
		try {
			Method readMethod = propertyDescriptor.getReadMethod();
			Object value      = readMethod.invoke(obj);
			
			if (value == null && createIfNotExists == true && containerProperty == true) {
				try {
					/**
					 * An instance of the property does not exist... so create one
					 */
					Method writeMethod = propertyDescriptor.getWriteMethod();
					Object newValue;
					Class  clazz = Class.forName(canonicalClassName);
					newValue = clazz.newInstance();
					writeMethod.invoke(obj, newValue);
					return(newValue);
				}
				catch (Exception x) {
					System.out.println("Exception creating a property [" + canonicalClassName + "]: " + x.toString());
					return(null);
				}
			}
			
			if (collectionClass == true && index != null) {
				
				if (value == null && createIfNotExists) {
					String className = null;
					try {
						/**
						 * An instance of the property does not exist... so create the collection object
						 */
						Method writeMethod = propertyDescriptor.getWriteMethod();
						Object newValue;
						Class  clazz;
						
						className = propertyDescriptor.getPropertyType().getCanonicalName();
						
						/**
						 * Convert the collection class name into a concrete implementation...
						 */
						if (className.equals("java.util.Set")) {
							className = "java.util.LinkedHashSet";
						}
						
						
						clazz    = Class.forName(className);
						newValue = clazz.newInstance();
						writeMethod.invoke(obj, newValue);
						return(newValue);
					}
					catch (Exception x) {
						System.out.println("Exception creating a collection [" + className + "]: " + x.toString());
						return(null);
					}
				}
				
				Object[] objArray = ((Collection)value).toArray();
				int      idx      = Integer.parseInt(index);
				
				/**
				 * If the indexed element does not exist in the collection we might need to create it...
				 */
				if (idx >= objArray.length && createIfNotExists == true) {
					Object newValue = null;
					Class  clazz;
					
					/**
					 * THIS MAY NEED TO CHANGE...
					 * I'M GOING TO CREATE ALL MISSING ELEMENTS UP TO THE SPECIFIED INDEX...
					 */
					for (int i = objArray.length; i <= idx; i++) {
						clazz = Class.forName(canonicalClassName);
						newValue = clazz.newInstance();
						((Collection)value).add(newValue);
					}
					
					return(newValue);
				}
				
				return(objArray[idx]);
			}
			
			return(value);
		}
		catch (Exception ex) {
			System.out.println("Exception reading property: " + ex.toString());
			return(null);
		}
	}
	
	public Object setPropertyValue(Object obj, Object value) {
		
		try {
			Method writeMethod = propertyDescriptor.getWriteMethod();
			
			/**
			 * value has to be parsed into the correct class type...
			 * TODO Do this properly and for all types...
			 */
			if (canonicalClassName.equals("java.lang.Integer")) {
				value = Integer.parseInt((String)value);
			}
			if (canonicalClassName.equals("java.lang.Character")) {
				value = new Character(value.toString().charAt(0));
			}
			if (canonicalClassName.equals("java.lang.Boolean")) {
				String strValue = value.toString();
				if (strValue.equalsIgnoreCase("Y") || strValue.equalsIgnoreCase("1")) {
					value = "true";
				}
				else
				if (strValue.equalsIgnoreCase("N") || strValue.equalsIgnoreCase("0")) {
					value = "false";
				}
				value = Boolean.parseBoolean((String)value);
			}
			
			value    = writeMethod.invoke(obj, value);
		}
		catch (Exception ex) {
			System.out.println("Exception setting a property value: " + ex.toString());
			return(null);
		}
		
		return(value);
	}
	
}

class PropertyPathTokens {
	
	ArrayList<String> elements;
	ArrayList<String> indexes;
	int               readIdx;
	
	public PropertyPathTokens(String propertyPath) {
		String[] toks = propertyPath.split("\\.");
		elements = new ArrayList<String>(toks.length);
		indexes  = new ArrayList<String>(toks.length);
		for (int i = 0; i < toks.length; i++) {
			String[] t = toks[i].split("\\[");
			elements.add(t[0]);
			if (t.length > 1) {
				indexes.add(t[1].substring(0, t[1].length()-1));
			}
			else {
				indexes.add(null);
			}
		}
		resetReadIndex();
	}
	
	public void resetReadIndex() {
		readIdx = 1;
	}
	
	public boolean hasNext() {
		return(readIdx < elements.size());
	}
	
	public String remainingTokensToString() {
		StringBuffer b = new StringBuffer();
		for (int i = readIdx-1; i < elements.size(); i++) {
			b.append(getElementAt(i));
			if (getIndexAt(i) != null) {
				b.append("[" + getIndexAt(i) + "]");
			}
			if (i < elements.size()-1) {
				b.append(".");
			}
		}
		return(b.toString());
	}
	
	public String getNextElement() {
		if (readIdx >= elements.size()) {
			return(null);
		}
		readIdx++;
		return(elements.get(readIdx-1));
	}
	
	public String getNextIndex() {
		if (readIdx >= indexes.size()) {
			return(null);
		}
		return(indexes.get(readIdx-1));
	}
	
	public String getElementAt(int index) {
		if (index >= 0 && index < elements.size()) {
			return(elements.get(index));
		}
		else {
			return(null);
		}
	}
	
	public String getIndexAt(int index) {
		if (index >= 0 && index < indexes.size()) {
			return(indexes.get(index));
		}
		else {
			return(null);
		}
	}
	
}
