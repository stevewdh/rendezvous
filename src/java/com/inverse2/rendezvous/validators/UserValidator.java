package com.inverse2.rendezvous.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.inverse2.rendezvous.model.User;

public class UserValidator implements Validator {

	/**
	 * This method returns true of this validator supports the passed
	 * in class.
	 */
	public boolean supports(Class clazz) {
		return(User.class.isAssignableFrom(clazz));
	}

	/**
	 * This method validates the passed in object.
	 */
	public void validate(Object obj, Errors errors) {
		if (obj == null) {
			return;
		}
		User user;
		try {
			user = (User) obj;
		}
		catch (Exception ex) {
			errors.reject("userValidator.error.castError");
			return;
		}
		/**
		 * Validate for any blank fields...
		 */
		ValidationUtils.rejectIfEmpty(errors, "username",    "userValidator.error.username.empty");
		ValidationUtils.rejectIfEmpty(errors, "firstName",   "userValidator.error.firstName.empty");
		ValidationUtils.rejectIfEmpty(errors, "surname",     "userValidator.error.surname.empty");
		
	}

}
