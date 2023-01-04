package br.com.test.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.test.api.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

public interface UserController {

	@GetMapping("/{id}")
	@Operation(summary = "Retorna User correspondente ao identificador recuperado por parametro")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json"))})
	ResponseEntity<UserDto> findById(@PathVariable Integer id);

	@GetMapping
	@Operation(summary = "Retorna a lista de User")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json"))})
	ResponseEntity<List<UserDto>> findAll();

	@PostMapping
	@Operation(summary = "Método responsável por criar User")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "devolvera no headers do request o caminho de acesso")})
	ResponseEntity<UserDto> create(@Valid @RequestBody UserDto dto);
	
	@PutMapping("/{id}")
	@Operation(summary = "Método responsável por alterar User")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json"))})
	ResponseEntity<UserDto> update(@PathVariable Integer id, @Valid @RequestBody UserDto dto);

	@DeleteMapping("/{id}")
	@Operation(summary = "Método responsável por excluir User pelo id")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Não retorna nada!")})
	ResponseEntity<Void> delete(@PathVariable Integer id);
}
