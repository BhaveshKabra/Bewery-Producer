package org.bhavesh.micro.repository;

import java.util.UUID;

import org.bhavesh.micro.web.bean.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, UUID>{
	
	
}
