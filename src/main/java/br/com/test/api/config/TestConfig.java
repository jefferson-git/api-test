package br.com.test.api.config;

import br.com.test.api.model.Usuario;
import br.com.test.api.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
@Profile("test")
public class TestConfig {
	private final UsuarioRepository repository;
	@Bean
	void CreateDados() {	

		var u1 = new Usuario(null, "jefferson", "007", "jefferson@gmail.com");
		var u2 = new Usuario(null, "maria", "004", "maria@gmail.com");
		repository.saveAll(Arrays.asList(u1, u2));
	}
}
