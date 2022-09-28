package com.Gemini1.Gemini1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="categoryApi",version="2.0",description="categoryApi"))
public class Gemini1Application {

	public static void main(String[] args) {
		SpringApplication.run(Gemini1Application.class, args);
	}

}
