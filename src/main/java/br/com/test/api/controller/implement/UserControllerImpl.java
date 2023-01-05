package br.com.test.api.controller.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.test.api.config.ModelMapperConfig;
import br.com.test.api.controller.UserController;
import br.com.test.api.dto.UserDto;
import br.com.test.api.service.implement.UserServiceImpl;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor	
@RequestMapping("/api/user")
public class UserControllerImpl implements UserController{

	private final ModelMapperConfig model;
	private final UserServiceImpl service;
	
	@Override
	public ResponseEntity<UserDto> findById(Integer id) {
		return ResponseEntity.ok().body(model.mapper().map(service.findById(id), UserDto.class));
	}

	@Override
	public ResponseEntity<List<UserDto>> findAll() {
		return ResponseEntity.ok().body(service.findAll().stream().map(x -> model.mapper().map(x, UserDto.class)).collect(Collectors.toList()));
	}

	@Override
	public ResponseEntity<UserDto> create(UserDto dto) {
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(model.mapper().map(service.create(dto), UserDto.class).getId()).toUri()).build();
	}

	@Override
	public ResponseEntity<UserDto> update(Integer id, UserDto dto) {
		return ResponseEntity.ok().body(model.mapper().map(service.update(id, dto), UserDto.class));
	}

	@Override
	public ResponseEntity<Void> delete(Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
