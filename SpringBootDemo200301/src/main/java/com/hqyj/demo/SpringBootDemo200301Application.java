package com.hqyj.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class SpringBootDemo200301Application extends SpringBootServletInitializer{
	
	

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootDemo200301Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemo200301Application.class, args);
	}

}
