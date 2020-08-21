package org.bhavesh.micro.mapper;

import org.bhavesh.micro.web.bean.Customer;
import org.bhavesh.micro.web.bean.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

	CustomerDTO customertoCustomerDTO(Customer customer);
	Customer customerDTOtoCustomer(CustomerDTO customerDTO);
}
