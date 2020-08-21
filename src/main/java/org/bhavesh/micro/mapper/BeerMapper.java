package org.bhavesh.micro.mapper;

import java.util.List;

import org.bhavesh.micro.web.bean.Beer;
import org.bhavesh.micro.web.bean.dto.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
	BeerDTO beertoBeerDTO(Beer beer);
	
	Beer beerDTOtoBeer(BeerDTO beerdto);
	
	List<BeerDTO> beerListtoBeerDTO(List<Beer> beerList);
	
	List<Beer> beerDTOListtoBeer(List<BeerDTO> beerDTOList);
}
