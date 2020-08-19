package org.bhavesh.micro.service;

import java.util.Optional;
import java.util.UUID;

import org.bhavesh.micro.repository.CustomerRepository;
import org.bhavesh.micro.web.bean.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	private CustomerRepository customerRepo;
	
	public CustomerService(CustomerRepository custRepository) {
		this.customerRepo=custRepository;
	}
	
	public Customer getCustomerbyId(UUID id)
	{
		Optional<Customer> optcustomer=customerRepo.findById(id);
		if(optcustomer.isPresent())
		{
			return optcustomer.get();
		}
		else
			return null;
	}
	public void updateCustomerbyId(UUID id,Customer customer)
	{
		Optional<Customer> optcustomer=customerRepo.findById(id);
		if(optcustomer.isPresent())
		{
			Customer existingcust=optcustomer.get();
			existingcust.setCustomername(customer.getCustomername());
			customerRepo.save(existingcust);
		}
	}
	
	public void saveCustomer(Customer customer)
	{
		Customer cust=Customer.builder().customername(customer.getCustomername())
		                                .build();
		customerRepo.save(cust);
	}
	public void deleteCustomerbyId(UUID id)
	{
		customerRepo.deleteById(id);
	}

	public Iterable<Customer> getAllCustomer() {
		
		return customerRepo.findAll();
	}
}
