package ua.com.social.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.social.demo.config.JpaConfig;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class[]{DemoApplication.class, JpaConfig.class}, args);
	}
}
