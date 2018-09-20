package com.bzh.disciplineder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //开启事务
@SpringBootApplication
public class DisciplinederApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisciplinederApplication.class, args);
	}
}
