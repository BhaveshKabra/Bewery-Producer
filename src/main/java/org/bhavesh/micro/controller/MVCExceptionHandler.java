package org.bhavesh.micro.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MVCExceptionHandler 
{
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException e)
	{
		List<String> errorlist=new ArrayList<String>(e.getConstraintViolations().size());
		e.getConstraintViolations().forEach(violation -> errorlist.add(violation.getPropertyPath()+":"+violation.getMessage()));
		return new ResponseEntity<List<String>>(errorlist,HttpStatus.BAD_REQUEST);
	}
}
