package org.bhavesh.micro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.bhavesh.micro.service.BeerService;
import org.bhavesh.micro.web.bean.Beer;
import org.bhavesh.micro.web.bean.dto.BeerDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/beer/")
public class BeerController {
	
	private BeerService beerService;
	
	public BeerController(BeerService beerService) {
		this.beerService=beerService;
	}
	
	@RequestMapping("{id}")
	public ResponseEntity<BeerDTO> getBeerbyID(@PathVariable("id")UUID id)
	{
		BeerDTO beer=beerService.getBeerbyID(id);
		if(beer!=null)
			return new ResponseEntity<BeerDTO> (beer,HttpStatus.OK);
		else
			return new ResponseEntity<BeerDTO>	(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<BeerDTO> updateBeerbyID(@PathVariable("id")UUID id,@Validated @RequestBody BeerDTO beer)
	{
		return new ResponseEntity<BeerDTO>(beerService.updateBeerbyID(id, beer),HttpStatus.NO_CONTENT);
	}
	
	@PostMapping
	public ResponseEntity<BeerDTO> putBeer(@Valid @RequestBody BeerDTO beer)
	{
		beerService.saveBeer(beer);
		HttpHeaders headers=new HttpHeaders();
		headers.add("Location", "/api/v1/beer/"+beer.getId().toString());
		return new ResponseEntity<BeerDTO>(headers,HttpStatus.CREATED);
	}
	
	@GetMapping("upc/{upcid}")
	public ResponseEntity<List<BeerDTO>> getbyUPC(@PathVariable("upcid") String upcid)
	{
		List<BeerDTO> beerlist=beerService.getBeerbyUpc(upcid);
		if(!beerlist.isEmpty())
		{
			return new ResponseEntity<List<BeerDTO>>(beerlist,HttpStatus.OK); 
		}
		else
		{
			return new ResponseEntity<List<BeerDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping
	public ResponseEntity<List<Beer>> getAllBeers()
	{
		List<Beer> listbeer=new ArrayList<Beer>();
		beerService.getAllbeer().forEach(listbeer::add);
		return new ResponseEntity<List<Beer>>(listbeer,HttpStatus.OK);
	}
	
	@DeleteMapping("/{deleteid}")
	public ResponseEntity<Beer> deletbyid(@PathVariable("deleteid") UUID deleteid )
	{
		beerService.deleteBeer(deleteid);
		return new ResponseEntity<Beer> (HttpStatus.NO_CONTENT);
	}
}
	
