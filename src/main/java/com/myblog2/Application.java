package com.myblog2;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//it is starting point from where  exectuction starts or indicates a configuration class thatdeclares one or more @bean methodsand also triggers auto configuration.
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
		@Bean
		public ModelMapper modelMapper() { // model mapper comes from external liberary
		return new ModelMapper();

	}

}
