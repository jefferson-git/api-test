package br.com.test.api.controller.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.test.api.config.ModelMapperConfig;
import br.com.test.api.controller.UsuarioController;
import br.com.test.api.dto.UsuarioDto;
import br.com.test.api.service.implement.UsuarioServiceImpl;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor	
@RequestMapping("/api/user")
public class UsuarioControllerImpl implements UsuarioController{

	private final ModelMapperConfig model;
	private final UsuarioServiceImpl service;
	
	@Override
	public ResponseEntity<UsuarioDto> findById(Integer id) {
		return ResponseEntity.ok().body(model.mapper().map(service.findById(id), UsuarioDto.class));
	}

	@Override
	public ResponseEntity<List<UsuarioDto>> findAll() {
		return ResponseEntity.ok().body(service.findAll().stream().map(x -> model.mapper().map(x, UsuarioDto.class)).collect(Collectors.toList()));
	}

	@Override
	public ResponseEntity<UsuarioDto> create(UsuarioDto dto) {
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(model.mapper().map(service.create(dto), UsuarioDto.class).getId()).toUri()).build();
	}

	@Override
	public ResponseEntity<UsuarioDto> update(Integer id, UsuarioDto dto) {
		return ResponseEntity.ok().body(model.mapper().map(service.update(id, dto), UsuarioDto.class));
	}

	@Override
	public ResponseEntity<Void> delete(Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
