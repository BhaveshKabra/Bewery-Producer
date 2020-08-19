package org.bhavesh.micro.repository;

import java.util.ArrayList;
import java.util.UUID;

import org.bhavesh.micro.web.bean.Beer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID>
{
	@Query(value="select b from Beer b where b.upc=:upc")
	ArrayList<Beer> getBeerbyUPC(@Param("upc")String upc);
	
	@Query(value="select b from Beer b")
	ArrayList<Beer> getAllBeer();
	
} 
