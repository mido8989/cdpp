package com.hyundaimotors.hmb.cdppapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan(basePackages = "com.hyundaimotors.hmb.cdppapp.mapper.**")
@SpringBootApplication
public class CdppAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdppAppApplication.class, args);
	}

}
