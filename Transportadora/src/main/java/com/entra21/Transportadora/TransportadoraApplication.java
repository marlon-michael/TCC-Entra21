package com.entra21.Transportadora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TransportadoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportadoraApplication.class, args);
	}

	@Configuration
	public static class SecurityConfig {
		@Bean
		public static PasswordEncoder passwordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}
	}

}
