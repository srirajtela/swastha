package com.jsp.swasthik.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwasthikServoConfig {
	
	@Bean
	public Docket getDocket() {
		
		//contact is from springfox.documentation.service.Contact
		Contact contact= new Contact("JSPIDERS","https://jspiders.com", "jsp@gmail.com");
		List<VendorExtension> extensions = new ArrayList<VendorExtension>();
		ApiInfo apiInfo =  new ApiInfo("Swasthaik", "a Web Application which connects Hospitals with blood donors"
				, "1.0", "terms", contact, "license", "liecenceurl", extensions);
		
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.jsp.swasthik"))
				.build().apiInfo(apiInfo).useDefaultResponseMessages(false)	;	
	}

}
