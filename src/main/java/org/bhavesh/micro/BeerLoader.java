package org.bhavesh.micro;

import org.bhavesh.micro.repository.BeerRepository;
import org.springframework.boot.CommandLineRunner;

public class BeerLoader implements CommandLineRunner {

	public final BeerRepository beerRepository;
	
	
	public BeerLoader(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadBeerObject();

	}

	private void loadBeerObject() {
		if(beerRepository.count()==0)
		{
			//sasve some beer
		}
		
	}

}
