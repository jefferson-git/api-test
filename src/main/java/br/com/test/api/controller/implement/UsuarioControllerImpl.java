package br.com.test.api.controller.implement;

import br.com.test.api.controller.UsuarioController;
import br.com.test.api.dto.UsuarioDto;
import br.com.test.api.service.implement.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

@RestController
@AllArgsConstructor	
@RequestMapping("/api/user")
public class UsuarioControllerImpl implements UsuarioController{

	private final ModelMapper model;
	private final UsuarioServiceImpl service;
	
	@Override
	public ResponseEntity<UsuarioDto> findById(Integer id) {
		return ResponseEntity.ok().body(model.map(service.findById(id), UsuarioDto.class));
	}

	@Override
	public ResponseEntity<List<UsuarioDto>> findAll() {
		return ResponseEntity.ok().body(service.findAll().stream()
			.map(x -> model.map(x, UsuarioDto.class)).collect(Collectors.toList()));
	}

	@Override
	public ResponseEntity<UsuarioDto> create(UsuarioDto dto) {
		return ResponseEntity.created(fromCurrentRequestUri().path("/{id}")
			.buildAndExpand(model.map(service.create(dto), UsuarioDto.class).getId()).toUri()).build();
	}

	@Override
	public ResponseEntity<UsuarioDto> update(Integer id, UsuarioDto dto) {
		return ResponseEntity.ok().body(model.map(service.update(id, dto), UsuarioDto.class));
	}

	@Override
	public ResponseEntity<Void> delete(Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
