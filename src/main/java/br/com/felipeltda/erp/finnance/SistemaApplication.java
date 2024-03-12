package br.com.felipeltda.erp.finnance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:env/local.properties")
public class SistemaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SistemaApplication.class, args);
	}

}
