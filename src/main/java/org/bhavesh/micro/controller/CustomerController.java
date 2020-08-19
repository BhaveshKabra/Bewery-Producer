package org.bhavesh.micro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.bhavesh.micro.service.CustomerService;
import org.bhavesh.micro.web.bean.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController 
{
	@Autowired
	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService=customerService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerbyId(@PathVariable("id") UUID id)
	{
		Customer cust=customerService.getCustomerbyId(id);
		if(cust!=null)
		{
			return new ResponseEntity<Customer>(cust,HttpStatus.FOUND);
		}
		else
		{
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);	
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> putCustomerbyId(@PathVariable("id")UUID id,@Valid @RequestBody Customer cust)
	{
		Customer customer=customerService.getCustomerbyId(id);
		if(customer!=null)
		{
			customer.setCustomername(cust.getCustomername());
			customerService.saveCustomer(customer);
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomer()
	{
		List<Customer> listcust=new ArrayList<Customer>();
		customerService.getAllCustomer().forEach(e->listcust.add(e));
		return new ResponseEntity<List<Customer>> (listcust,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer)
	{
		UUID id=UUID.randomUUID();
		Customer savecustomer=Customer.builder().customername(customer.getCustomername())
		                  .id(id)
		                  .build();
		customerService.saveCustomer(savecustomer);
		HttpHeaders headers=new HttpHeaders();
		headers.add("location", id.toString());
		return new ResponseEntity<Customer>(headers,HttpStatus.CREATED);
	}
	
}
