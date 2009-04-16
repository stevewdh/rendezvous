package com.inverse2.rendezvous.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.inverse2.rendezvous.model.Building;

public class BuildingValidator implements Validator {

	/**
	 * This method returns true of this validator supports the passed
	 * in class.
	 */
	public boolean supports(Class clazz) {
		return(Building.class.isAssignableFrom(clazz));
	}

	/**
	 * This method validates the passed in object.
	 */
	public void validate(Object obj, Errors errors) {
		if (obj == null) {
			return;
		}
		Building building;
		try {
			building = (Building) obj;
		}
		catch (Exception ex) {
			errors.reject("buildingValidator.error.castError");
			return;
		}
		/**
		 * Validate that the building name is not blank.
		 */
		ValidationUtils.rejectIfEmpty(errors, "buildingName", "buildingValidator.error.buildingName.empty");
		/**
		 * Validate that the country code is not blank...
		 */
		ValidationUtils.rejectIfEmpty(errors, "country.countryCode", "buildingValidator.error.countryCode.empty");
		
	}

}
