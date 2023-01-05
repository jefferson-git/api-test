package br.com.test.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.test.api.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional <User> findByEmail(String email);
}
