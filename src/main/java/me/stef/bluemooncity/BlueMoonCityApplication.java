package me.stef.bluemooncity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
public class BlueMoonCityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlueMoonCityApplication.class, args);
	}

}
