package br.com.test.api.config;

import br.com.test.api.model.User;
import br.com.test.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
@Profile("test")
public class TestConfig {
	private final UserRepository repository;
	@Bean
	void CreateDados() {	

		var u1 = new User(null, "jefferson", "007", "jefferson@gmail.com");
		var u2 = new User(null, "maria", "004", "maria@email.com");
		repository.saveAll(Arrays.asList(u1, u2));
	}
}
