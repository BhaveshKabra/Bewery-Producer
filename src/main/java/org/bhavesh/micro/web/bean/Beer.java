package org.bhavesh.micro.web.bean;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Beer 
{
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)",updatable = false,nullable = false)
	UUID id;
	@NotBlank
	String beerName;
	@NotBlank
	BeerStyleEnum beerStyle;
	@Column(unique = true)
	String upc;
	Long qunatityonhand;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	BigDecimal price;
	
	@Version
	Integer version;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ",shape = JsonFormat.Shape.STRING)
	@CreationTimestamp
	@Column(updatable = false)
	OffsetDateTime createdDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ",shape = JsonFormat.Shape.STRING)
	@UpdateTimestamp
	OffsetDateTime lastModtifiedDate;
}
