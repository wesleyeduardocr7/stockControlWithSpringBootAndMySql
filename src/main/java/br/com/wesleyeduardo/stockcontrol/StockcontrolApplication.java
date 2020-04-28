package br.com.wesleyeduardo.stockcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class StockcontrolApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockcontrolApplication.class, args);
	}

}
