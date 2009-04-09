package com.inverse2.test.validators;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.inverse2.rendezvous.model.Building;
import com.inverse2.rendezvous.model.LkCountryCode;
import com.inverse2.rendezvous.validators.BuildingValidator;

public class BuildingValidatorTest extends TestCase {

	/**
	 * Check that the validator knows which classes it can validate...
	 */
	public void testSupports() {
		System.out.println("** Test the supports method...");
		try {
			BuildingValidator v = new BuildingValidator();
			assertEquals("Validator should support Building class",   true,  v.supports(Building.class));
			assertEquals("Validator should not support Object class", false, v.supports(Object.class));
		}
		catch (Exception ex) {
			fail("Exception testing supports method: " + ex.toString());
		}
	}

	public void testValidateNullInput() {
		System.out.println("** Test the validate method - null input...");
		BuildingValidator v = new BuildingValidator();
		Errors            e = new BeanPropertyBindingResult(new Object(), "Test");
		ObjectError       err;
		List              l;
		Iterator          i;
		
		try {
			v.validate(null, e);
		}
		catch (Exception ex) {
			fail("The validate method should support null object being passed in.");
		}
	}

	public void testValidateWrongObjectTypeInput() {
		System.out.println("** Test the validate method - wrong object type...");
		BuildingValidator v = new BuildingValidator();
		Errors            e = new BeanPropertyBindingResult(new Object(), "Test");
		ObjectError       err;
		List              l;
		Iterator          i;
		
		// Pass in an invalid object type...
		try {
			v.validate(new Object(), e);
			assertEquals("One error message expected", 1, e.getErrorCount());
			
			l = e.getAllErrors();
			i = l.iterator();
			
			err = (ObjectError) i.next();
			assertEquals("Unexpected error code", "buildingValidator.error.castError", err.getCode());
		}
		catch (Exception ex) {
			fail(ex.toString());
		}
	}
	
	public void testValidateBlankBuildingName() {
		System.out.println("** Test the validate method - blank building name...");
		
		try {
			SimpleDateFormat  dp = new SimpleDateFormat("yyyy-MM-dd");
			Building          b  = new Building(new LkCountryCode("GBR"), "", "88 Fleet Street", "", "", "London", "WC1 1BB", dp.parse("2001-01-01"), dp.parse("2100-01-01"), new HashSet(0));
			BuildingValidator v = new BuildingValidator();
			Errors            e = new BeanPropertyBindingResult(b, "Test");
			ObjectError       err;
			List              l;
			Iterator          i;
			
			// Pass in an invalid object type...
			v.validate(b, e);
			assertEquals("One error message expected", 1, e.getErrorCount());
			
			l = e.getAllErrors();
			i = l.iterator();
			
			err = (ObjectError) i.next();
			assertEquals("Unexpected error code", "buildingValidator.error.buildingName.empty", err.getCode());
		}
		catch (Exception ex) {
			fail(ex.toString());
		}
	}

	public void testValidateBlankCountryCode() {
		System.out.println("** Test the validate method - blank country code...");
		
		try {
			SimpleDateFormat  dp = new SimpleDateFormat("yyyy-MM-dd");
			Building          b  = new Building(new LkCountryCode(""), "Building Name", "88 Fleet Street", "", "", "London", "WC1 1BB", dp.parse("2001-01-01"), dp.parse("2100-01-01"), new HashSet(0));
			BuildingValidator v = new BuildingValidator();
			Errors            e = new BeanPropertyBindingResult(b, "Test");
			ObjectError       err;
			List              l;
			Iterator          i;
			
			// Pass in an invalid object type...
			v.validate(b, e);
			assertEquals("One error message expected", 1, e.getErrorCount());
			
			l = e.getAllErrors();
			i = l.iterator();
			
			err = (ObjectError) i.next();
			assertEquals("Unexpected error code", "buildingValidator.error.countryCode.empty", err.getCode());
		}
		catch (Exception ex) {
			fail(ex.toString());
		}
	}
	
}
