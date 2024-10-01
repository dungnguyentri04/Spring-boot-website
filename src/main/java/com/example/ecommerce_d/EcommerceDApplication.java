package com.example.ecommerce_d;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceDApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceDApplication.class, args);// trả về một  đại diện cho IOC
	}

}
