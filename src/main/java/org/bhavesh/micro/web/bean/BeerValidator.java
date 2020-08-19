package org.bhavesh.micro.web.bean;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BeerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Beer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "beerName", "beerName.empty");

	}

}
