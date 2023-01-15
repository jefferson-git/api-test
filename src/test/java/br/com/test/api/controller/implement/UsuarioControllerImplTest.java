package br.com.test.api.controller.implement;

import br.com.test.api.dto.UsuarioDto;
import br.com.test.api.model.Usuario;
import br.com.test.api.service.implement.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioControllerImplTest {

    private static final Integer INDEX   = 0;
    public static final Integer ID = 1;
    public static final String NOME = "jefferson";
    public static final String PASSWORD = "123";
    public static final String EMAIL = "jefin@gmail";

    private Usuario usuario;
    private UsuarioDto dto;

    @InjectMocks
    private UsuarioControllerImpl controller;

    @Mock
    private UsuarioServiceImpl service;

    @Mock
    private ModelMapper model;
    
    @BeforeEach
    void setUp() {
        startUsuario();
    }


    @Test
    @DisplayName("Get - Busca por usuário pelo id")
    void whenFindByIdThenSuccess() {
        when(service.findById(anyInt())).thenReturn(usuario);
        when(model.map(any(), any())).thenReturn(dto);

        var  response = controller.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsuarioDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    @DisplayName("Get - Lista de Usuários")
    void whenFindAllThenReturnAListOfUserDTO() {
        when(service.findAll()).thenReturn(List.of(usuario));
        when(model.map(any(), any())).thenReturn(dto);

        var response = controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UsuarioDto.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
    }


    @Test
    @DisplayName("Post - Criando um novo Usuário")
    void create() {
        when(service.create(dto)).thenReturn(usuario);
        when(model.map(any(), any())).thenReturn(dto);

        var response = controller.create(dto);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertNotNull(response.getHeaders().get("Location"));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Put - Atualizando um Usuário")
    void update() {
        when(service.update(ID, dto)).thenReturn(usuario);
        when(model.map(any(), any())).thenReturn(dto);

        var response = controller.update(ID, dto);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsuarioDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void delete() {
    }

    private void startUsuario() {
        usuario = new Usuario(ID, NOME, PASSWORD, EMAIL);
        dto = new UsuarioDto(ID, NOME, PASSWORD, EMAIL);
    }
}