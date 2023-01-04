package br.com.test.api.controller.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserDto> create(UserDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserDto> update(Integer id, UserDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
