package org.bhavesh.micro.service;

import java.util.List;
import java.util.UUID;

import org.bhavesh.micro.mapper.BeerMapper;
import org.bhavesh.micro.repository.BeerRepository;
import org.bhavesh.micro.web.bean.Beer;
import org.bhavesh.micro.web.bean.dto.BeerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BeerService 
{
	@Autowired
	private BeerRepository beerRepository;
	private final BeerMapper beerMapper;
	
	
	public BeerService(BeerRepository beerRepository,BeerMapper mapper)
	{
		this.beerMapper = mapper;
		this.beerRepository=beerRepository;
	}
	public BeerDTO getBeerbyID(UUID id)
	{
		return beerMapper.beertoBeerDTO(beerRepository.findById(id).orElseThrow());
	}
	public BeerDTO updateBeerbyID(UUID id,BeerDTO beerdto)
	{	
		Beer beer=beerRepository.findById(id).orElseThrow();
		beer.setBeerName(beerdto.getBeerName());
		beer.setBeerStyle(beerdto.getBeerStyle());
		beer.setUpc(beerdto.getUpc());
		beer.setPrice(beerdto.getPrice());
		return beerMapper.beertoBeerDTO(beerRepository.save(beer));	
	}
	public BeerDTO saveBeer(BeerDTO beerDto)
	{
			return beerMapper.beertoBeerDTO(beerRepository.save(beerMapper.beerDTOtoBeer(beerDto)));	
	}	
	public void deleteBeer(UUID id)
	{
		beerRepository.deleteById(id);
	}
	
	public List<BeerDTO> getBeerbyUpc(String upc)
	{
		return beerMapper.beerListtoBeerDTO(beerRepository.getBeerbyUPC(upc));
	}
	
	public Iterable<Beer> getAllbeer()
	{
		return beerRepository.findAll();
	}
}
