package com.inverse2.rendezvous.databinding;

import java.io.Serializable;

import org.springframework.beans.ConfigurablePropertyAccessor;
import org.springframework.validation.AbstractPropertyBindingResult;

public class MasterDetailBeanPropertyBindingResult extends AbstractPropertyBindingResult implements Serializable {

	private final Object target;

	private transient MasterDetailBeanWrapper beanWrapper;

	public MasterDetailBeanPropertyBindingResult(Object target, String objectName) {
		super(objectName);
		this.target = target;
	}
	
	/**
	 * Return my own implementation of a ConfiguratablePropertyAccessor
	 */
	public final ConfigurablePropertyAccessor getPropertyAccessor() {
		if (beanWrapper == null) {
			beanWrapper = new MasterDetailBeanWrapper();
			beanWrapper.setWrappedInstance(target);
			beanWrapper.setExtractOldValueForEditor(true);
		}
		return(beanWrapper);
	}

	public Object getTarget() {
		return(target);
	}

}
