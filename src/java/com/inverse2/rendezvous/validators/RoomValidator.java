package com.inverse2.rendezvous.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.inverse2.rendezvous.model.Room;

public class RoomValidator implements Validator {

	public boolean supports(Class clazz) {
		return(Room.class.isAssignableFrom(clazz));
	}

	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub

	}

}
