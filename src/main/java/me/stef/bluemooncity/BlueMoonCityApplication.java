package me.stef.bluemooncity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BlueMoonCityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlueMoonCityApplication.class, args);
	}

}
