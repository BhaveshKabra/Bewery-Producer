package org.bhavesh.micro.web.bean.dto;

import java.math.BigDecimal;
import java.util.UUID;

import org.bhavesh.micro.web.bean.BeerStyleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerDTO {
	UUID id;
	String beerName;
	BeerStyleEnum beerStyle;
	String upc;
	BigDecimal price;
}
