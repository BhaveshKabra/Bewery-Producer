package org.bhavesh.micro.configure;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import({SpringDataRestConfiguration.class,BeanValidatorPluginsConfiguration.class})
public class ApplicationSwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact(
	        "Bewery App", "https://github.com/BhaveshKabra/Bewery-Producer", "kabrabhavesh12@gmail.com");

	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
	        "Bewery API", "Bewery API Description", "1.0",
	        "urn:tos", DEFAULT_CONTACT,
	        "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
	        new HashSet<String>(Arrays.asList("application/json"));

	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	            .apiInfo(DEFAULT_API_INFO)
	            .produces(DEFAULT_PRODUCES_AND_CONSUMES)
	            .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	 }
}