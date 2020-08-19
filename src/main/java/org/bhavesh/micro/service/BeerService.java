package org.bhavesh.micro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bhavesh.micro.repository.BeerRepository;
import org.bhavesh.micro.web.bean.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeerService 
{
	@Autowired
	private BeerRepository beerRepository;
	
	public BeerService(BeerRepository beerRepository)
	{
		this.beerRepository=beerRepository;
	}
	public Beer getBeerbyID(UUID id)
	{
		Optional<Beer> optBeer=beerRepository.findById(id);
		if(optBeer.isPresent())
		{	
			return optBeer.get();
		}
		else
		{
			return null;
		}
	}
	public void updateBeerbyID(UUID id,Beer beer)
	{
		if(beerRepository.existsById(id))
		{
			Beer saveBeer=Beer.builder().beerName(beer.getBeerName())
						  .beerStyle(beer.getBeerStyle())
						  .qunatityonhand(beer.getQunatityonhand())
						  .upc(beer.getUpc())
						  .build();
			beerRepository.save(saveBeer);
		}	
	}
	public void saveBeer(Beer beer)
	{
			Beer saveBeer=Beer.builder().beerName(beer.getBeerName())
						  .beerStyle(beer.getBeerStyle())
						  .qunatityonhand(beer.getQunatityonhand())
						  .upc(beer.getUpc())
						  .build();
			beerRepository.save(saveBeer);	
	}
	
	public void deleteBeer(UUID id)
	{
		beerRepository.deleteById(id);
	}
	
	public List<Beer> getBeerbyUpc(String upc)
	{
		ArrayList<Beer> beerlist=beerRepository.getBeerbyUPC(upc);
		return beerlist;
	}
	
	public Iterable<Beer> getAllbeer()
	{
		return beerRepository.findAll();
	}
}
