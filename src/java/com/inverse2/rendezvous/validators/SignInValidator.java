package com.inverse2.rendezvous.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.inverse2.rendezvous.model.User;

public class SignInValidator implements Validator {

	public boolean supports(Class clazz) {
		return(User.class.isAssignableFrom(clazz));
	}

	public void validate(Object command, Errors errors) {
		
		// TODO Implement validation...
		
		
		
	}

}
