package br.com.test.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.test.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional <Usuario> findByEmail(String email);
}
