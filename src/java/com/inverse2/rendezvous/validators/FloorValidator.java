package com.inverse2.rendezvous.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.inverse2.rendezvous.model.Floor;

public class FloorValidator implements Validator {

	public boolean supports(Class clazz) {
		return(Floor.class.isAssignableFrom(clazz));
	}

	public void validate(Object object, Errors error) {
		// TODO Auto-generated method stub

	}

}
