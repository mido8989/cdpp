package com.hyundaimotors.hmb.cdppapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@MapperScan(basePackages = "com.hyundaimotors.hmb.cdppapp.mapper.**")
@SpringBootApplication
public class CdppAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdppAppApplication.class, args);
	}
}
