package br.com.test.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.test.api.dto.UsuarioDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

public interface UsuarioController {

	@GetMapping("/{id}")
	@Operation(summary = "Retorna Usuario correspondente ao identificador recuperado por parametro")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json"))})
	ResponseEntity<UsuarioDto> findById(@PathVariable Integer id);

	@GetMapping
	@Operation(summary = "Retorna a lista de Usuario")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json"))})
	ResponseEntity<List<UsuarioDto>> findAll();

	@PostMapping
	@Operation(summary = "Método responsável por criar Usuario")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "devolvera no headers do request o caminho de acesso")})
	ResponseEntity<UsuarioDto> create(@Valid @RequestBody UsuarioDto dto);
	
	@PutMapping("/{id}")
	@Operation(summary = "Método responsável por alterar Usuario")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json"))})
	ResponseEntity<UsuarioDto> update(@PathVariable Integer id, @Valid @RequestBody UsuarioDto dto);

	@DeleteMapping("/{id}")
	@Operation(summary = "Método responsável por excluir Usuario pelo id")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Não retorna nada!")})
	ResponseEntity<Void> delete(@PathVariable Integer id);
}
