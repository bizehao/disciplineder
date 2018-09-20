package com.bzh.disciplineder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class DisciplinederApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisciplinederApplication.class, args);
	}
}
