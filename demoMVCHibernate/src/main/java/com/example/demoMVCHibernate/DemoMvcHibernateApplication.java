package com.example.demoMVCHibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:applicationContext.xml"})
public class DemoMvcHibernateApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoMvcHibernateApplication.class, args);
	}

}
