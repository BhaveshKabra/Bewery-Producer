package org.bhavesh.micro.controller;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.bhavesh.micro.service.BeerService;
import org.bhavesh.micro.web.bean.Beer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Beer> getBeerbyID(@PathVariable("id")UUID id)
	{
		Beer beer=beerService.getBeerbyID(id);
		if(beer!=null)
			return new ResponseEntity<Beer> (beer,HttpStatus.OK);
		else
			return new ResponseEntity<Beer>	(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Beer> updateBeerbyID(@PathVariable("id")UUID id,@Valid @RequestBody Beer beer)
	{
		Beer updatebeer;
		if(beerService.getBeerbyID(id)!=null)
		{
		   updatebeer=Beer.builder().beerName(beer.getBeerName())
							   .beerStyle(beer.getBeerStyle())
							   .upc(beer.getUpc())
							   .lastModtifiedDate(OffsetDateTime.now())
							   .qunatityonhand(beer.getQunatityonhand())
							   .price(beer.getPrice())
							   .id(id)
							   .build();
			return new ResponseEntity<Beer> (updatebeer,HttpStatus.NO_CONTENT);
		}
		else
			return new ResponseEntity<Beer>	(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Beer> putBeer(@Valid @RequestBody Beer beer)
	{
		beer.setLastModtifiedDate(OffsetDateTime.now());
		beer.setCreatedDate(OffsetDateTime.now());
		beer.setId(UUID.randomUUID());
		beerService.saveBeer(beer);
		HttpHeaders headers=new HttpHeaders();
		headers.add("Location", "/api/v1/beer/"+beer.getId().toString());
		return new ResponseEntity<Beer>(headers,HttpStatus.CREATED);
	}
	
	@GetMapping("upc/{upcid}")
	public ResponseEntity<List<Beer>> getbyUPC(@PathVariable("upcid") String upcid)
	{
		List<Beer> beerlist=beerService.getBeerbyUpc(upcid);
		if(!beerlist.isEmpty())
		return new ResponseEntity<List<Beer>>(beerlist,HttpStatus.OK); 
		else
			return new ResponseEntity<List<Beer>>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping
	public ResponseEntity<List<Beer>> getAll()
	{
		List<Beer> listbeer=new ArrayList<Beer>();
		beerService.getAllbeer().forEach(e->listbeer.add(e));
		return new ResponseEntity<List<Beer>>(listbeer,HttpStatus.OK);
	}
	
	@DeleteMapping("/{deleteid}")
	public ResponseEntity<Beer> deletbyid(@PathVariable("deleteid") UUID deleteid )
	{
		beerService.deleteBeer(deleteid);
		return new ResponseEntity<Beer> (HttpStatus.NO_CONTENT);
	}
	

}
	
